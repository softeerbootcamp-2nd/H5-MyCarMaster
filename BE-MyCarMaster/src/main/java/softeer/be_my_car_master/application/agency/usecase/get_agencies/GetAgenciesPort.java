package softeer.be_my_car_master.application.agency.usecase.get_agencies;

import java.util.List;

import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetAgenciesPort {

	List<Agency> findAgenciesInGu(String gu);
}
