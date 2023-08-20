package softeer.be_my_car_master.application.color_interior.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetInteriorColorsResponse {

	private List<InteriorColorDto> interiorColors;

	public static GetInteriorColorsResponse from(List<InteriorColor> selectableColors) {
		List<InteriorColorDto> interiorColorDtos = selectableColors.stream()
			.map(InteriorColorDto::from)
			.collect(Collectors.toList());
		return new GetInteriorColorsResponse(interiorColorDtos);
	}
}
