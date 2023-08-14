package softeer.be_my_car_master.api.option.dto.response;

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
public class GetDefaultOptionsResponse {

	private List<DefaultOptionDto> defaultOptions;

	public static GetDefaultOptionsResponse from(List<Option> defaultOptions) {
		List<DefaultOptionDto> defaultOptionsDto = defaultOptions.stream()
			.map(DefaultOptionDto::from)
			.collect(Collectors.toList());
		return new GetDefaultOptionsResponse(defaultOptionsDto);
	}
}
