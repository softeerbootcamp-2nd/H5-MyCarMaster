package softeer.be_my_car_master.application.consult.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.consulting.Consulting;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetConsultingsResponse {

	private List<ConsultingDto> consultings;

	public static GetConsultingsResponse from(List<Consulting> consultings) {
		List<ConsultingDto> consultingDtos = consultings.stream()
			.map(ConsultingDto::from)
			.collect(Collectors.toList());
		return new GetConsultingsResponse(consultingDtos);
	}
}
