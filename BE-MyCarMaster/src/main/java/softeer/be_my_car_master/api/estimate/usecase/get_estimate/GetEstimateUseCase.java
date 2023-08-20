package softeer.be_my_car_master.api.estimate.usecase.get_estimate;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.api.estimate.exception.InvalidEstimationException;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetEstimateUseCase {

	private final GetEstimatePort port;

	public GetEstimateResponse execute(UUID estimateUuid) {
		Estimate estimate = port.findFullEstimateByUuid(estimateUuid)
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
		return GetEstimateResponse.from(estimate);
	}
}
