package softeer.bemycarmaster.api.domain.wheeldrive.usecase;

import softeer.bemycarmaster.api.domain.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetWheelDrivesUseCase {

	public GetWheelDrivesResponse execute(Integer trimId) {

		return new GetWheelDrivesResponse();
	}
}
