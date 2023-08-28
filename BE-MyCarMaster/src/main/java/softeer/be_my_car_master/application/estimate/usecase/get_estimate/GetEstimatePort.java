package softeer.be_my_car_master.application.estimate.usecase.get_estimate;

import java.util.Optional;
import java.util.UUID;

import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetEstimatePort {

	Optional<Estimate> findFullEstimateByUuid(UUID estimateUuid);
}
