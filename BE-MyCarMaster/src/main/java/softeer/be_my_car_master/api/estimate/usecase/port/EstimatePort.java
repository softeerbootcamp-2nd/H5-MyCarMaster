package softeer.be_my_car_master.api.estimate.usecase.port;

import softeer.be_my_car_master.api.estimate.dto.request.CreateEstimateRequest;

public interface EstimatePort {

	Long createEstimate(CreateEstimateRequest createEstimateRequest);
}
