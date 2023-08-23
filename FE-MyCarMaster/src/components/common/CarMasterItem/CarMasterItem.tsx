import {
  Agency,
  CarMasterImage,
  CarMasterInfo,
  CarMasterInfoContainer,
  CarMasterIntro,
  CarMasterName,
  CarMasterPhoneNum,
  Container,
} from "./style";

interface CarMasterProps {
  id: number;
  agency: {
    id: number;
    name: string;
  };
  imgUrl: string;
  intro: string;
  name: string;
  phone: string;
  carMasterId: number;
  setCarMasterId: (carMasterId: number) => void;
}

function CarMasterItem({
  id,
  agency,
  imgUrl,
  intro,
  name,
  phone,
  carMasterId,
  setCarMasterId,
}: CarMasterProps) {
  const selectCarMasterHandler = () => {
    if (carMasterId === id) {
      setCarMasterId(0);
    } else {
      setCarMasterId(id);
    }
  };

  return (
    <Container onClick={selectCarMasterHandler} $isClicked={carMasterId === id}>
      <CarMasterImage src={imgUrl} />
      <CarMasterInfoContainer>
        <CarMasterInfo>
          <CarMasterName>{name}</CarMasterName>
          <CarMasterPhoneNum>{phone}</CarMasterPhoneNum>
        </CarMasterInfo>
        <CarMasterIntro>{intro}</CarMasterIntro>
      </CarMasterInfoContainer>
      <Agency>{agency.name}</Agency>
    </Container>
  );
}

export default CarMasterItem;
