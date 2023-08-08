package softeer.be_my_car_master.api.engine.usecase;

import softeer.be_my_car_master.api.engine.dto.response.GetEnginesResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetEnginesUseCase {

	public GetEnginesResponse execute(Long trimId) {
		return new GetEnginesResponse();
	}
}
