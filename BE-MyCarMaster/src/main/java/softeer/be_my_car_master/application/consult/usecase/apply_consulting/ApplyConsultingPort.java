package softeer.be_my_car_master.application.consult.usecase.apply_consulting;

import java.util.Optional;
import java.util.UUID;

import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface ApplyConsultingPort {

	Optional<Estimate> findEstimateByUuid(UUID estimateUuid);

	Optional<CarMaster> findCarMasterById(Long carMasterId);

	void createConsulting(Consulting consulting);
}
