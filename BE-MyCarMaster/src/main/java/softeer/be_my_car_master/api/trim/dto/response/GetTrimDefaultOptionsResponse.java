package softeer.be_my_car_master.api.trim.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public GetTrimDefaultOptionsResponse from(List<Option> defaultOptions, List<String> needs) {

		List<TrimDefaultOptionDto> trimDefaultOptionDtos = IntStream.range(0, defaultOptions.size())
			.mapToObj(i -> TrimDefaultOptionDto.from(defaultOptions.get(i), needs.get(i)))
			.collect(Collectors.toList());
		return new GetTrimDefaultOptionsResponse(trimDefaultOptionDtos);
	}
}
