package softeer.be_my_car_master.api.consult.usecase;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.consult.exception.InvalidCarMasterIdException;
import softeer.be_my_car_master.api.consult.exception.InvalidEstimateIdException;
import softeer.be_my_car_master.api.consult.handler.MailSendEvent;
import softeer.be_my_car_master.api.consult.usecase.port.CarMasterPort;
import softeer.be_my_car_master.api.consult.usecase.port.ConsultingPort;
import softeer.be_my_car_master.api.estimate.usecase.port.EstimatePort;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class ApplyConsultingUseCase {

	private final ConsultingPort consultingPort;
	private final CarMasterPort carMasterPort;
	private final EstimatePort estimatePort;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public void execute(
		UUID estimateId,
		Long carMasterId,
		String clientName,
		String clientEmail,
		String clientPhone
	) {
		// 카마스터 구매 상담 신청
		Estimate estimate =
			estimatePort.findByUuid(estimateId).orElseThrow(() -> InvalidEstimateIdException.EXCEPTION);
		CarMaster carMaster
			= carMasterPort.findById(carMasterId).orElseThrow(() -> InvalidCarMasterIdException.EXCEPTION);

		Consulting consulting = Consulting.create(clientName, clientEmail, clientPhone, estimate, carMaster);
		consultingPort.createConsulting(consulting);

		// 이메일 전송
		eventPublisher.publishEvent(new MailSendEvent(estimateId, clientEmail));
	}
}
