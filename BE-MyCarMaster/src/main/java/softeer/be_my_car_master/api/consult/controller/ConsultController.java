package softeer.be_my_car_master.api.consult.controller;

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
@RequestMapping("/consult")
@RequiredArgsConstructor
@Tag(name = "Consult Color", description = "Consult API Document")
public class ConsultController {

	private final ApplyConsultingUseCase applyConsultingUseCase;

	@PostMapping
	public Response applyConsulting(@RequestBody @Valid ApplyConsultingRequest applyConsultingRequest) {
		String estimateId = applyConsultingRequest.getEstimateId();
		String clientName = applyConsultingRequest.getClientName();
		String clientEmail = applyConsultingRequest.getClientEmail();
		String clientPhone = applyConsultingRequest.getClientPhone();

		applyConsultingUseCase.execute(estimateId, clientName, clientEmail, clientPhone);

		return Response.createSuccessResponse();
	}
}
