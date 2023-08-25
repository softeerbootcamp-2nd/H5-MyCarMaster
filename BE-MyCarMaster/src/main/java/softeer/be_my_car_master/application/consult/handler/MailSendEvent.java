package softeer.be_my_car_master.application.consult.handler;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MailSendEvent {

	private UUID estimateId;
	private String clientEmail;
	private Long consultingId;
}
