package softeer.be_my_car_master.application.trim.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.trim.Trim;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTrimsResponse {

	private List<TrimDto> trims;

	public static GetTrimsResponse from(List<Trim> trim) {
		List<TrimDto> trimDtos = trim.stream()
			.map(TrimDto::from)
			.collect(Collectors.toList());
		return new GetTrimsResponse(trimDtos);
	}
}
