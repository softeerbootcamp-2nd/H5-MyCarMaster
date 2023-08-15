package softeer.be_my_car_master.api.consult.usecase;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.consult.exception.InvalidCarMasterIdException;
import softeer.be_my_car_master.api.consult.exception.InvalidEstimateIdException;
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

	public void execute(
		UUID estimateId,
		Long carMasterId,
		String clientName,
		String clientEmail,
		String clientPhone
	) {
		// 카마스터 - 유저 상담 신청 정보
		Estimate estimate = estimatePort.findById(estimateId).orElseThrow(() -> InvalidEstimateIdException.EXCEPTION);
		CarMaster carMaster = carMasterPort.findById(carMasterId)
			.orElseThrow(() -> InvalidCarMasterIdException.EXCEPTION);

		Consulting consulting = Consulting.create(clientName, clientEmail, clientPhone, estimate, carMaster);
		consultingPort.createConsulting(consulting);

		// 이메일 전송
	}
}
