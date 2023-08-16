package softeer.be_my_car_master.api.consult.usecase.port;

import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface ConsultingPort {

	void createConsulting(Consulting consulting);
}
