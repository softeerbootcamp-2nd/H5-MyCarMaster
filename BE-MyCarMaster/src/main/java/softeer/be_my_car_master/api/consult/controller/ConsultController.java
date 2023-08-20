package softeer.be_my_car_master.api.consult.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.consult.dto.request.ApplyConsultingRequest;
import softeer.be_my_car_master.api.consult.usecase.apply_consulting.ApplyConsultingUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Consulting", description = "consulting API Document")
public class ConsultController {

	private final ApplyConsultingUseCase applyConsultingUseCase;

	@PostMapping("/consultings")
	public Response applyConsulting(@RequestBody @Valid ApplyConsultingRequest request) {
		UUID estimateId = UUID.fromString(request.getEstimateId());
		Long carMasterId = request.getCarMasterId();
		String clientName = request.getClientName();
		String clientEmail = request.getClientEmail();
		String clientPhone = request.getClientPhone();

		applyConsultingUseCase.execute(estimateId, carMasterId, clientName, clientEmail, clientPhone);

		return Response.createSuccessResponse();
	}
}
