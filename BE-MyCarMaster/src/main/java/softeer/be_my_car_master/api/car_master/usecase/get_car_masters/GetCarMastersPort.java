package softeer.be_my_car_master.api.car_master.usecase.get_car_masters;

import java.util.List;

import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetCarMastersPort {

	List<Agency> findAgenciesAndCarMasters(Double latitude, Double longitude);
}
