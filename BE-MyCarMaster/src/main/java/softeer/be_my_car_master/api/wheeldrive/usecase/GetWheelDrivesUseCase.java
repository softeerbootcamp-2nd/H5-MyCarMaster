package softeer.be_my_car_master.api.wheeldrive.usecase;

import softeer.be_my_car_master.api.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
public class GetWheelDrivesUseCase {

	public GetWheelDrivesResponse execute(Long trimId) {

		return new GetWheelDrivesResponse();
	}
}
