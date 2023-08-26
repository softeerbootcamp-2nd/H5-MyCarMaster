package softeer.be_my_car_master.application.consult.handler;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSendHandler {

	@Value(value = "${spring.mail.link}")
	private String estimateLink;

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;
	private final MailSendPort mailSendPort;

	@Async
	@TransactionalEventListener(
		phase = TransactionPhase.AFTER_COMMIT,
		classes = MailSendEvent.class
	)
	public void publicApplyConsultingEvent(MailSendEvent mailSendEvent) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		String text = makeText(mailSendEvent.getEstimateId(), mailSendEvent.getClientName());

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
		mimeMessageHelper.setTo(mailSendEvent.getClientEmail());
		mimeMessageHelper.setSubject("[Hyundai] " + mailSendEvent.getClientName() + "님의 구매 상담 신청이 접수되었습니다.");
		mimeMessageHelper.setText(text, true); // 메일 본문 내용, HTML 여부
		mailSender.send(mimeMessage);

		mailSendPort.sendComplete(mailSendEvent.getConsultingId());
	}

	private String makeText(UUID estimateId, String clientName) {
		String clientEstimateLink = estimateLink + estimateId.toString();
		Context context = new Context();
		context.setVariable("clientEstimateLink", clientEstimateLink);
		context.setVariable("clientName", clientName);
		return templateEngine.process("mail", context);
	}
}
