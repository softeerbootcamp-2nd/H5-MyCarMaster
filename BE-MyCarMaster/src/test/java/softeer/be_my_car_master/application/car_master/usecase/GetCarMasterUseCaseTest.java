package softeer.be_my_car_master.application.car_master.usecase;

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

import softeer.be_my_car_master.application.car_master.dto.response.AgencyDto;
import softeer.be_my_car_master.application.car_master.dto.response.CarMasterDto;
import softeer.be_my_car_master.application.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.application.car_master.usecase.get_car_masters.GetCarMasterUseCase;
import softeer.be_my_car_master.application.car_master.usecase.get_car_masters.GetCarMastersPort;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetCarMasterUseCase Test")
class GetCarMasterUseCaseTest {

	@InjectMocks
	private GetCarMasterUseCase useCase;

	@Mock
	private GetCarMastersPort port;

	@Test
	@DisplayName("카마스터 목록을 조회합니다")
	void execute() {
		// given
		CarMaster carMaster = CarMaster.builder()
			.id(1L)
			.name("이몽룡")
			.imgUrl("imgUrl")
			.phone("010-0000-0000")
			.sales(60)
			.intro("안녕하세요")
			.email("test@test.com")
			.build();

		Agency agency = Agency.builder()
			.id(1L)
			.name("한양대리점")
			.gu("성동구")
			.latitude(32.1212)
			.longitude(127.1212)
			.carMasters(Arrays.asList(carMaster))
			.build();
		carMaster.setAgency(agency);

		given(port.findAgenciesAndCarMasters(any(), any())).willReturn(Arrays.asList(agency));

		// when
		GetCarMasterResponse response = useCase.execute(32.1212, 127.1545);

		// then
		List<AgencyDto> agencyDtos = response.getAgencies();
		AgencyDto expectedAgency = agencyDtos.get(0);
		List<CarMasterDto> carMasterDtos = response.getCarMasters();
		CarMasterDto expectedCarMaster = carMasterDtos.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(agencyDtos).isNotNull();
			softAssertions.assertThat(agencyDtos).hasSize(1);
			softAssertions.assertThat(expectedAgency.getId()).isEqualTo(agency.getId());
			softAssertions.assertThat(expectedAgency.getGu()).isEqualTo(agency.getGu());
			softAssertions.assertThat(expectedCarMaster.getName()).isEqualTo(carMaster.getName());
			softAssertions.assertThat(expectedCarMaster.getIntro()).isEqualTo(carMaster.getIntro());
			softAssertions.assertThat(expectedCarMaster.getPhone()).isEqualTo(carMaster.getPhone());
		});
	}
}
