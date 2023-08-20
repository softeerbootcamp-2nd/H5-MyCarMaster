package softeer.be_my_car_master.application.agency.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.agency.Agency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAgenciesResponse {

	private List<AgencyInGuDto> agencies;

	public static GetAgenciesResponse from(List<Agency> agencies) {
		List<AgencyInGuDto> agencyInGuDtos = agencies.stream()
			.map(AgencyInGuDto::from)
			.collect(Collectors.toList());
		return new GetAgenciesResponse(agencyInGuDtos);
	}
}
