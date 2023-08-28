package softeer.be_my_car_master.application.consult.usecase.get_consultings;

import java.util.List;
import java.util.Optional;

import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetConsultingsPort {

	Optional<Long> findCarMasterIdByForm(String email, String phone);

	List<Consulting> findConsultingsByCarMaster(Long carMasterId);
}
