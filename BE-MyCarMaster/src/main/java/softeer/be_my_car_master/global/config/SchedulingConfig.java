package softeer.be_my_car_master.global.config;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import softeer.be_my_car_master.application.consult.handler.MailSendEvent;
import softeer.be_my_car_master.application.consult.handler.MailSendPort;
import softeer.be_my_car_master.domain.consulting.Consulting;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulingConfig {

	private final MailSendPort mailSendPort;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	@Scheduled(cron = "0 */1 * * * *")
	public void setDiaryEditStatus() {
		List<Consulting> sendFailureConsultings = mailSendPort.findSendFailureConsultings();
		sendEmails(sendFailureConsultings);
	}

	private void sendEmails(List<Consulting> sendFailureConsultings) {
		sendFailureConsultings.stream()
				.forEach(consulting -> {
					UUID uuid = consulting.getUuid();
					String clientEmail = consulting.getClientEmail();
					Long id = consulting.getId();
					eventPublisher.publishEvent(new MailSendEvent(uuid, clientEmail, id));
				});
	}
}
