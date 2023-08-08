package softeer.be_my_car_master.api.color_interior.usecase;

import softeer.be_my_car_master.api.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetInteriorColorsUseCase {

	public GetInteriorColorsResponse execute(Integer trimId, Integer exteriorColorId) {
		return new GetInteriorColorsResponse();
	}
}
