package softeer.be_my_car_master.api.body_type.usecase;

import softeer.be_my_car_master.api.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetBodyTypesUseCase {

	public GetBodyTypesResponse execute(Long modelId) {

		return new GetBodyTypesResponse();
	}
}
