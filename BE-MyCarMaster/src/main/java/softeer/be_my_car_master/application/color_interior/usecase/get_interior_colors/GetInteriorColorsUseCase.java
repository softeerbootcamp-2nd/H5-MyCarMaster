package softeer.be_my_car_master.application.color_interior.usecase.get_interior_colors;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetInteriorColorsUseCase {

	private final GetInteriorColorsPort port;

	public GetInteriorColorsResponse execute(Long trimId, Long exteriorColorId) {
		List<InteriorColor> interiorColors = port.findInteriorColorsByTrim(trimId);
		List<Long> unselectableInteriorColorIds =
			port.findUnselectableInteriorColorIdsByExteriorColor(exteriorColorId);

		List<InteriorColor> selectableInteriorColors = interiorColors.stream()
			.filter(interiorColor -> interiorColor.isSelectable(unselectableInteriorColorIds))
			.collect(Collectors.toList());

		return GetInteriorColorsResponse.from(selectableInteriorColors);
	}
}
