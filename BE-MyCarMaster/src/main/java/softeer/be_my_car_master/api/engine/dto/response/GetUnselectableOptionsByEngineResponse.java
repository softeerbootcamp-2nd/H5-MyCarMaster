package softeer.be_my_car_master.api.engine.dto.response;

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
public class GetUnselectableOptionsByEngineResponse {

	private List<UnselectableOptionDto> unselectableOptions;

	public static GetUnselectableOptionsByEngineResponse from(List<Option> unselectableOptionsByEngine) {
		List<UnselectableOptionDto> unselectableOptionDtos = unselectableOptionsByEngine.stream()
			.map(UnselectableOptionDto::from)
			.collect(Collectors.toList());
		return new GetUnselectableOptionsByEngineResponse(unselectableOptionDtos);
	}
}
