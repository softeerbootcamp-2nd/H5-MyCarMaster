package softeer.be_my_car_master.api.consult.usecase.port;

import java.util.List;
import java.util.Optional;

import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface CarMasterPort {

	Optional<CarMaster> findById(Long estimateId);

	// List<CarMaster> findCarMasters(Double latitude, Double longitude, String filter);
}
