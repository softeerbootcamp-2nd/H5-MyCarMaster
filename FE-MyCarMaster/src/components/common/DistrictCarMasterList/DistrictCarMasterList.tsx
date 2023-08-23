import { useEffect, useState } from "react";
import { styled } from "styled-components";
import { get } from "../../../utils/fetch";
import theme from "../../../styles/Theme";
import { AgencyType, CarMasterType } from "../../../types/map.types";
import CarMasterItem from "../CarMasterItem/CarMasterItem";

interface DistrictCarMasterProps {
  district: string;
  setDistrict: (district: string) => void;
  setAgencies: (agencies: AgencyType[]) => void;
  carMasters: CarMasterType[];
  carMasterId: number;
  setCarMasterId: (carMasterId: number) => void;
}

function DistrictCarMasterList({
  district,
  setDistrict,
  setAgencies,
  carMasters,
  carMasterId,
  setCarMasterId,
}: DistrictCarMasterProps) {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const [districtCarMasters, setDistrictCarMasters] =
    useState<CarMasterType[]>();

  useEffect(() => {
    get(`${SERVER_URL}/agencies?gu=${district}`).then((res) =>
      setAgencies(res.result.agencies)
    );
  }, []);

  useEffect(() => {
    setDistrictCarMasters(carMasters);
  }, [carMasters]);

  const backButtonHandler = () => {
    setDistrict("");
  };

  return (
    <Container>
      <BackButton onClick={backButtonHandler}>지도로 돌아가기</BackButton>
      {districtCarMasters &&
        districtCarMasters.map((carMaster) => (
          <CarMasterItem
            key={carMaster.id}
            {...carMaster}
            carMasterId={carMasterId}
            setCarMasterId={setCarMasterId}
          />
        ))}
    </Container>
  );
}

export default DistrictCarMasterList;

export const Container = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  gap: 1rem;

  overflow-y: auto;
`;

export const BackButton = styled.button`
  height: 2rem;

  color: ${theme.colors.NAVYBLUE5};
  font-family: "HyundaiSansRegular";
  font-size: 0.875rem;
  font-style: normal;
  font-weight: 500;
`;
