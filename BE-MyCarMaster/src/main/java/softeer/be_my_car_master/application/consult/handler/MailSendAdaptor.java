package softeer.be_my_car_master.application.consult.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.ConsultingEntity;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.ConsultingJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class MailSendAdaptor implements MailSendPort {

	private final ConsultingJpaRepository consultingJpaRepository;

	@Override
	@Transactional
	public void sendComplete(Long consultingId) {
		ConsultingEntity consulting = consultingJpaRepository.findById(consultingId).get();
		consulting.sendEmail();
	}

	@Override
	public List<Consulting> findSendFailureConsultings() {
		return consultingJpaRepository.findAllByIsSent(false).stream()
			.map(ConsultingEntity::toConsulting)
			.collect(Collectors.toList());
	}
}
