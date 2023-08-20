package softeer.be_my_car_master.application.car_master.usecase.get_car_masters_in_agency;

import java.util.List;

import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetCarMastersInAgencyPort {

	List<CarMaster> findCarMastersByAgency(Long agencyId);
}
