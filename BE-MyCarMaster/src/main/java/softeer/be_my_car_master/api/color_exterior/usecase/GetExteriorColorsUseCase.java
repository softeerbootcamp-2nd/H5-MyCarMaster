package softeer.be_my_car_master.api.color_exterior.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.color_exterior.dto.response.GetExteriorColorsResponse;
import softeer.be_my_car_master.api.color_exterior.usecase.port.ExteriorColorPort;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetExteriorColorsUseCase {

	private final ExteriorColorPort exteriorColorPort;

	public GetExteriorColorsResponse execute(Long trimId) {
		List<ExteriorColor> selectableExteriorColors = exteriorColorPort.findSelectableExteriorColorsByTrimId(trimId);
		return GetExteriorColorsResponse.from(selectableExteriorColors);
	}
}
