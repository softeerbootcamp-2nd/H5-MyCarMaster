package softeer.be_my_car_master.api.engine.usecase;

import java.util.List;

import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetUnselectableOptionsByEngineUseCase {

	public GetUnselectableOptionsByEngineResponse execute(Long engineId, Long trimId, List<Long> optionIds) {
		return new GetUnselectableOptionsByEngineResponse();
	}
}
