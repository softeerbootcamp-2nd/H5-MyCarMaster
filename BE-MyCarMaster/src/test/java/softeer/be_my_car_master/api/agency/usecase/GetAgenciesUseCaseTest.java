package softeer.be_my_car_master.api.agency.usecase;

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

import softeer.be_my_car_master.api.agency.dto.response.AgencyInGuDto;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.api.agency.usecase.get_agencies.GetAgenciesPort;
import softeer.be_my_car_master.api.agency.usecase.get_agencies.GetAgenciesUseCase;
import softeer.be_my_car_master.domain.agency.Agency;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetAgenciesUseCase Test")
class GetAgenciesUseCaseTest {

	@InjectMocks
	private GetAgenciesUseCase useCase;

	@Mock
	private GetAgenciesPort port;

	@Test
	@DisplayName("대리점 목록을 조회합니다")
	void execute() {
		// given
		Agency agency = Agency.builder()
			.id(1L)
			.name("한양대리점")
			.gu("성동구")
			.latitude(32.1212)
			.longitude(127.1212)
			.build();

		given(port.findAgenciesInGu(any())).willReturn(Arrays.asList(agency));

		// when
		GetAgenciesResponse response = useCase.execute("성동구");

		// then
		List<AgencyInGuDto> agencyInGuDtos = response.getAgencies();
		AgencyInGuDto expectedAgency = agencyInGuDtos.get(0);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(agencyInGuDtos).isNotNull();
			softAssertions.assertThat(agencyInGuDtos).hasSize(1);
			softAssertions.assertThat(expectedAgency.getId()).isEqualTo(agency.getId());
			softAssertions.assertThat(expectedAgency.getName()).isEqualTo(agency.getName());
			softAssertions.assertThat(expectedAgency.getLatitude()).isEqualTo(agency.getLatitude());
			softAssertions.assertThat(expectedAgency.getLongitude()).isEqualTo(agency.getLongitude());
		});
	}
}
