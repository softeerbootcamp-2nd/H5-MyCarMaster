package softeer.be_my_car_master.api.estimate.usecase;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.api.estimate.exception.InvalidEstimationException;
import softeer.be_my_car_master.api.estimate.usecase.port.EstimatePort;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetEstimateUseCase {

	private final EstimatePort estimatePort;

	public GetEstimateResponse execute(UUID estimateId) {
		Estimate estimate = estimatePort.findFullEstimateByUuid(estimateId)
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
		return GetEstimateResponse.from(estimate);
	}
}
