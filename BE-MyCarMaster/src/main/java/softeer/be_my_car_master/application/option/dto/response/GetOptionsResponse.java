package softeer.be_my_car_master.application.option.dto.response;

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
public class GetOptionsResponse {

	private List<OptionDto> options;
	private List<String> exclusiveTags;

	public static GetOptionsResponse from(List<Option> filteredSelectableOptions, List<String> exclusiveTags) {
		List<OptionDto> optionDtos = filteredSelectableOptions.stream()
			.map(OptionDto::from)
			.collect(Collectors.toList());
		return new GetOptionsResponse(optionDtos, exclusiveTags);
	}
}
