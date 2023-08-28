package softeer.be_my_car_master.application.car_master.usecase.get_car_masters;

import java.util.List;

import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetCarMastersPort {

	List<Agency> findAgenciesByLocation(Double latitude, Double longitude);

	List<CarMaster> findCarMastersByAgenciesOrderBySales(List<Long> agencyIds);
}
