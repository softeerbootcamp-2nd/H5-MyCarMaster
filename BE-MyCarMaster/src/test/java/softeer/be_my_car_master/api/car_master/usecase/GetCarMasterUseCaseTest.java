package softeer.be_my_car_master.api.car_master.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import softeer.be_my_car_master.api.car_master.dto.request.FilterEnum;
import softeer.be_my_car_master.api.car_master.dto.response.CarMasterDto;
import softeer.be_my_car_master.api.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.api.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.api.color_interior.dto.response.InteriorColorDto;
import softeer.be_my_car_master.api.consult.usecase.port.CarMasterPort;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetCarMasterUseCase Test")
class GetCarMasterUseCaseTest {

	@InjectMocks
	private GetCarMasterUseCase getCarMasterUseCase;

	@Mock
	private CarMasterPort carMasterPort;

	@Test
	@DisplayName("카마스터 목록을 조회합니다")
	void execute() {
		// given
		Agency agency = Agency.builder()
			.id(1L)
			.name("한양대리점")
			.gu("성동구")
			.build();

		CarMaster carMaster = CarMaster.builder()
			.id(1L)
			.name("이몽룡")
			.imgUrl("imgUrl")
			.phone("010-0000-0000")
			.sales(60)
			.intro("안녕하세요")
			.email("test@test.com")
			.agency(agency)
			.build();

		given(carMasterPort.findCarMasters(any(), any(), any())).willReturn(Arrays.asList(carMaster));

		// when
		GetCarMasterResponse getCarMasterResponse =
			getCarMasterUseCase.execute(32.1212, 127.1545, FilterEnum.DISTANCE.getValue());

		// then
		List<CarMasterDto> carMasterDtos = getCarMasterResponse.getCarMasters();
		CarMasterDto expected = carMasterDtos.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(carMasterDtos).isNotNull();
			softAssertions.assertThat(carMasterDtos).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(carMaster.getId());
			softAssertions.assertThat(expected.getName()).isEqualTo(carMaster.getName());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(carMaster.getImgUrl());
			softAssertions.assertThat(expected.getPhone()).isEqualTo(carMaster.getPhone());
			softAssertions.assertThat(expected.getIntro()).isEqualTo(carMaster.getIntro());
			softAssertions.assertThat(expected.getAgency()).isEqualTo(carMaster.getAgencyName());
		});
	}
}
