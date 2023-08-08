package softeer.be_my_car_master.api.color_exterior.usecase;

import softeer.be_my_car_master.api.color_exterior.dto.response.GetExteriorColorsResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetExteriorColorsUseCase {

	public GetExteriorColorsResponse execute(Integer trimId) {
		return new GetExteriorColorsResponse();
	}
}
