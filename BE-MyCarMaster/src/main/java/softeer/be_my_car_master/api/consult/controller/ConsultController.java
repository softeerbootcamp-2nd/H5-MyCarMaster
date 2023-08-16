package softeer.be_my_car_master.api.consult.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.consult.dto.request.ApplyConsultingRequest;
import softeer.be_my_car_master.api.consult.usecase.ApplyConsultingUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/consulting")
@RequiredArgsConstructor
@Tag(name = "Consulting", description = "consulting API Document")
public class ConsultController {

	private final ApplyConsultingUseCase applyConsultingUseCase;

	@PostMapping
	public Response applyConsulting(@RequestBody @Valid ApplyConsultingRequest applyConsultingRequest) {
		UUID estimateId = UUID.fromString(applyConsultingRequest.getEstimateId());
		Long carMasterId = applyConsultingRequest.getCarMasterId();
		String clientName = applyConsultingRequest.getClientName();
		String clientEmail = applyConsultingRequest.getClientEmail();
		String clientPhone = applyConsultingRequest.getClientPhone();

		applyConsultingUseCase.execute(estimateId, carMasterId, clientName, clientEmail, clientPhone);

		return Response.createSuccessResponse();
	}
}
