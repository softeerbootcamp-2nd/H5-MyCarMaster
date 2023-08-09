package softeer.be_my_car_master.api.color_interior.usecase;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.api.color_interior.usecase.port.InteriorColorPort;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetInteriorColorsUseCase {

	private final InteriorColorPort interiorColorPort;

	public GetInteriorColorsResponse execute(Long trimId, Long exteriorColorId) {
		List<InteriorColor> interiorColors = interiorColorPort.findSelectableInteriorColorsByTrimId(trimId);
		List<Long> unselectableInteriorColorIds =
			interiorColorPort.findUnselectableInteriorColorIdsByExteriorColorId(exteriorColorId);

		List<InteriorColor> selectableInteriorColors = interiorColors.stream()
			.filter(interiorColor -> interiorColor.isSelectable(unselectableInteriorColorIds))
			.collect(Collectors.toList());

		return GetInteriorColorsResponse.from(selectableInteriorColors);
	}
}
