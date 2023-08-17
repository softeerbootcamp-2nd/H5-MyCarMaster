package softeer.be_my_car_master.api.car_master.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface AgencyPort {

	List<Agency> findAgenciesAndCarMasters(Double latitude, Double longitude);
}
