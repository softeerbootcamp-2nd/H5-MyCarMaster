package softeer.be_my_car_master.application.consult.handler;

import java.util.List;

import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface MailSendPort {

	void sendComplete(Long consultingId);

	List<Consulting> findSendFailureConsultings();
}
