package softeer.be_my_car_master.api.color_exterior.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetExteriorColorsResponse {

	private List<ExteriorColorDto> colors;

	public static GetExteriorColorsResponse from(List<ExteriorColor> selectableColors) {
		List<ExteriorColorDto> exteriorColorDtos = selectableColors.stream()
			.map(ExteriorColorDto::from)
			.collect(Collectors.toList());
		return new GetExteriorColorsResponse(exteriorColorDtos);
	}
}
