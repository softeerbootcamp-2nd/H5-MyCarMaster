package softeer.be_my_car_master.application.agency.usecase;

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

import softeer.be_my_car_master.application.agency.dto.response.CarMasterInAgencyDto;
import softeer.be_my_car_master.application.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.application.car_master.usecase.get_car_masters_in_agency.GetCarMastersInAgencyPort;
import softeer.be_my_car_master.application.car_master.usecase.get_car_masters_in_agency.GetCarMastersInAgencyUseCase;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetCarMastersInAgencyUseCase Test")
class GetCarMastersInAgencyUseCaseTest {

	@InjectMocks
	private GetCarMastersInAgencyUseCase useCase;

	@Mock
	private GetCarMastersInAgencyPort port;

	@Test
	@DisplayName("대리점에 속한 카마스터 목록을 조회합니다")
	void execute() {
		// given
		Agency agency = Agency.builder()
			.id(1L)
			.name("한양대리점")
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

		given(port.findCarMastersByAgency(any())).willReturn(Arrays.asList(carMaster));

		// when
		GetCarMastersInAgencyResponse response = useCase.execute(1L);

		// then
		List<CarMasterInAgencyDto> carMasterDtos = response.getCarMasters();
		CarMasterInAgencyDto expected = carMasterDtos.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(carMasterDtos).isNotNull();
			softAssertions.assertThat(carMasterDtos).hasSize(1);
			softAssertions.assertThat(expected.getId()).isEqualTo(carMaster.getId());
			softAssertions.assertThat(expected.getImgUrl()).isEqualTo(carMaster.getImgUrl());
			softAssertions.assertThat(expected.getName()).isEqualTo(carMaster.getName());
			softAssertions.assertThat(expected.getIntro()).isEqualTo(carMaster.getIntro());
			softAssertions.assertThat(expected.getPhone()).isEqualTo(carMaster.getPhone());
		});
	}
}
