package softeer.be_my_car_master.api.trim.usecase;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetTrimDefaultOptionsUseCase {

	public GetTrimDefaultOptionsResponse execute(Long trimId) {
		return null;
	}
}
