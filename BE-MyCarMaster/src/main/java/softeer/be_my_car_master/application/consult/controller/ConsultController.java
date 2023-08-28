package softeer.be_my_car_master.application.consult.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.consult.dto.request.ApplyConsultingRequest;
import softeer.be_my_car_master.application.consult.dto.request.GetConsultingsRequest;
import softeer.be_my_car_master.application.consult.dto.response.GetConsultingsResponse;
import softeer.be_my_car_master.application.consult.usecase.apply_consulting.ApplyConsultingUseCase;
import softeer.be_my_car_master.application.consult.usecase.get_consultings.GetConsultingsUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Consulting", description = "consulting API Document")
public class ConsultController {

	private final ApplyConsultingUseCase applyConsultingUseCase;
	private final GetConsultingsUseCase getConsultingsUseCase;

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

	@GetMapping("/consultings")
	public Response getConsultings(@ParameterObject @Valid GetConsultingsRequest request) {
		String carMasterEmail = request.getEmail();
		String carMasterPhone = request.getPhone();
		GetConsultingsResponse response = getConsultingsUseCase.execute(carMasterEmail, carMasterPhone);
		return Response.createSuccessResponse(response);
	}
}
