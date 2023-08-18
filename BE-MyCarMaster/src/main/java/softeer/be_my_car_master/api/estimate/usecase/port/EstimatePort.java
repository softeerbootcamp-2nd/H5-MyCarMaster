package softeer.be_my_car_master.api.estimate.usecase.port;

import java.util.Optional;
import java.util.UUID;

import softeer.be_my_car_master.api.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.domain.estimate.Estimate;

public interface EstimatePort {

	UUID createEstimate(CreateEstimateRequest createEstimateRequest);

	Optional<Estimate> findByUuid(UUID estimateUuid);

	Optional<Estimate> findFullEstimateByUuid(UUID estimateId);
}
