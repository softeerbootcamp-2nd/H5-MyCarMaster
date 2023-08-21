package softeer.be_my_car_master.application.trim.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.option.Option;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTrimDefaultOptionsResponse {

	private List<TrimDefaultOptionDto> defaultOptions;

	public static GetTrimDefaultOptionsResponse from(List<Option> defaultOptions) {
		List<TrimDefaultOptionDto> trimDefaultOptionDtos = defaultOptions.stream()
			.map(TrimDefaultOptionDto::from)
			.collect(Collectors.toList());
		return new GetTrimDefaultOptionsResponse(trimDefaultOptionDtos);
	}
}
