/* eslint-disable */

import { Fragment, useEffect, useRef, useState } from "react";
import theme from "../../../styles/Theme";
import Button from "../Button/Button";
import { get } from "../../../utils/fetch";
import CarMasterItem from "../CarMasterItem/CarMasterItem";
import { AgencyType, CarMasterType } from "../../../types/map.types";
import MarkerImg from "../../../assets/images/MarkerImage.png";
import {
  Address,
  AddressContainer,
  CarMasterContainer,
  CarMasterHeader,
  CarMasterList,
  CarMasters,
  CarMastersDescription,
  ChangeAddress,
  CloseButton,
  Container,
  Description,
  KakaoMap,
  MapContainer,
  ModalOverlay,
  ShowAllCarMasterBtn,
  Title,
} from "./style";
import SeoulMap from "../../SeoulMap/SeoulMap";
import DistrictCarMasterList from "../DistrictCarMasterList/DistrictCarMasterList";
import { FormModal } from "../FormModal/FormModal";
const { kakao } = window;

declare global {
  interface Window {
    kakao: any;
  }
}

interface MapModalProps {
  setIsMapModalOpen: (isMapModalOpen: boolean) => void;
  estimateId: string;
}

function MapModal({ setIsMapModalOpen, estimateId }: MapModalProps) {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const [myLocation, setMyLocation] = useState({
    center: {
      latitude: 0,
      longitude: 0,
    },
    errMsg: "",
    isLoading: true,
  });
  const [isKakaoMapLoaded, setIsKakaoMapLoaded] = useState(false);
  const isAddressSet = !!myLocation;
  const [address, setAddress] = useState<string>();
  const [agencies, setAgencies] = useState<AgencyType[]>();
  const [allCarMasters, setAllCarMasters] = useState<CarMasterType[]>();
  const [carMasters, setCarMasters] = useState<CarMasterType[]>();
  const [carMasterId, setCarMasterId] = useState<number>(0);
  const [district, setDistrict] = useState<string>("");
  const [formModalOn, setFormModalOn] = useState<boolean>(false);
  const [agency, setAgency] = useState<AgencyType>({
    id: 0,
    name: "",
    gu: "",
    latitude: 0,
    longitude: 0,
  });
  const mapRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    setIsModalOpen(true);
  }, []);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(async (position) => {
        setMyLocation((prev) => ({
          ...prev,
          center: {
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          },
          isLoading: false,
        }));
        await getAddress(position.coords);
        await get(
          `${SERVER_URL}/car-masters?latitude=${position.coords.latitude}&longitude=${position.coords.longitude}`
        ).then((res) => {
          setAgencies(res.result.agencies);
          setAllCarMasters(res.result.carMasters);
          setCarMasters(res.result.carMasters);
        });
      });
    } else {
      setMyLocation((prev) => ({
        ...prev,
        errMsg: "위치 정보를 가져올 수 없습니다.",
        isLoading: false,
      }));
    }
  }, []);

  useEffect(() => {
    if (!isKakaoMapLoaded) {
      kakao.maps.load(() => {
        setIsKakaoMapLoaded(true);
      });
    }
  }, [isKakaoMapLoaded]);

  useEffect(() => {
    if (isKakaoMapLoaded) {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(
          myLocation.center.latitude,
          myLocation.center.longitude
        ),
        level: 4,
      };
      mapRef.current = new kakao.maps.Map(container, options);

      // 마커 생성
      const myPosition = new kakao.maps.LatLng(
        myLocation.center.latitude,
        myLocation.center.longitude
      );
      new kakao.maps.Marker({ map: mapRef.current, position: myPosition });

      agencies &&
        agencies.map((agency) => {
          const marker = new kakao.maps.Marker({
            map: mapRef.current,
            position: new kakao.maps.LatLng(agency.latitude, agency.longitude),
            image: new kakao.maps.MarkerImage(
              MarkerImg,
              new kakao.maps.Size(30, 40)
            ),
          });
          kakao.maps.event.addListener(marker, "click", async function () {
            await get(`${SERVER_URL}/agencies/${agency.id}/car-masters`).then(
              (res) => {
                setCarMasters(res.result.carMasters);
                setAgency(agency);
              }
            );
          });
        });
    }
  }, [isKakaoMapLoaded, agencies]);

  const closeMapModal = () => {
    setIsModalOpen(false);
    setIsMapModalOpen(false);
  };

  const consultHandler = () => {
    setFormModalOn(true);
  };

  const showAllHandler = () => {
    setCarMasters(allCarMasters);
    setAgency({ id: 0, name: "", gu: "", latitude: 0, longitude: 0 });
  };

  const getAddress = async (position: {
    longitude: number;
    latitude: number;
  }) => {
    const geocoder = new kakao.maps.services.Geocoder();
    const callback = async (result: any, status: string) => {
      if (status === kakao.maps.services.Status.OK) {
        result[0].road_address === null
          ? setAddress(result[0].address.address_name)
          : setAddress(result[0].road_address.address_name);
      }
    };
    geocoder.coord2Address(position.longitude, position.latitude, callback);
  };

  return (
    <Fragment>
      {isModalOpen && (
        <ModalOverlay>
          <Container>
            <CloseButton onClick={closeMapModal} />
            <MapContainer>
              <KakaoMap id="map" ref={mapRef}></KakaoMap>
              {isAddressSet && (
                <CarMasterContainer>
                  <CarMasterHeader>
                    <Title>카마스터 찾기</Title>
                    <AddressContainer>
                      <Address>
                        {myLocation.isLoading
                          ? "위치 정보를 불러오는 중입니다."
                          : address}
                      </Address>
                      <ChangeAddress>위치 수정</ChangeAddress>
                    </AddressContainer>
                  </CarMasterHeader>
                  {myLocation.isLoading ? (
                    <div>로딩 중</div>
                  ) : (
                    <CarMasters>
                      <CarMastersDescription>
                        <Description>
                          {carMasters
                            ? agency!.name === ""
                              ? "10km 반경 판매량 순으로 정렬한 카마스터입니다."
                              : `${agency!.name}의 카마스터입니다.`
                            : "주변에 카마스터가 없습니다. 구 단위로 확인해보세요."}
                        </Description>
                        {carMasters && (
                          <ShowAllCarMasterBtn onClick={showAllHandler}>
                            전체 카마스터 보기
                          </ShowAllCarMasterBtn>
                        )}
                      </CarMastersDescription>
                      <CarMasterList>
                        {carMasters ? (
                          carMasters.map((carMaster) => (
                            <CarMasterItem
                              key={carMaster.id}
                              {...carMaster}
                              carMasterId={carMasterId}
                              setCarMasterId={setCarMasterId}
                            />
                          ))
                        ) : district === "" ? (
                          <SeoulMap setDistrict={setDistrict} />
                        ) : (
                          <DistrictCarMasterList
                            district={district}
                            setDistrict={setDistrict}
                            setAgencies={setAgencies}
                            carMasters={carMasters! as CarMasterType[]}
                            carMasterId={carMasterId}
                            setCarMasterId={setCarMasterId}
                          />
                        )}
                      </CarMasterList>
                    </CarMasters>
                  )}
                </CarMasterContainer>
              )}
            </MapContainer>
            <Button
              $x={9.625}
              $y={2.25}
              $backgroundcolor={theme.colors.NAVYBLUE5}
              $bordercolor={theme.colors.NAVYBLUE5}
              $textcolor={theme.colors.WHITE}
              text="구매 상담 신청"
              handleClick={() => consultHandler()}
            />
          </Container>
        </ModalOverlay>
      )}
      {formModalOn && (
        <FormModal
          estimateId={estimateId}
          carMasterId={carMasterId}
          setFormModalOn={setFormModalOn}
        />
      )}
    </Fragment>
  );
}

export default MapModal;
