package softeer.be_my_car_master.api.estimate.usecase.port;

import softeer.be_my_car_master.api.estimate.dto.request.MakeUpEstimateRequest;

public interface EstimatePort {

	Long makeUpEstimate(MakeUpEstimateRequest makeUpEstimateRequest);
}
