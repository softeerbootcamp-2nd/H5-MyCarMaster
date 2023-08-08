package softeer.be_my_car_master.api.option.usecase;

import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetOptionsUseCase {

	public GetOptionsResponse execute(Integer modelId, Integer trimId, Integer engineId) {
		return new GetOptionsResponse();
	}
}
