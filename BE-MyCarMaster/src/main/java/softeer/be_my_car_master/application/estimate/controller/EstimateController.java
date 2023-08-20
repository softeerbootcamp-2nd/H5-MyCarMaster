package softeer.be_my_car_master.application.estimate.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.application.estimate.dto.response.CreateEstimateResponse;
import softeer.be_my_car_master.application.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.application.estimate.usecase.create_estimate.CreateEstimateUseCase;
import softeer.be_my_car_master.application.estimate.usecase.get_estimate.GetEstimateUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Estimate", description = "Estimate API Document")
public class EstimateController {

	private final CreateEstimateUseCase createEstimateUseCase;
	private final GetEstimateUseCase getEstimateUseCase;

	@PostMapping("/estimates")
	@Operation(summary = "선택한 내용들로 견적서를 생성합니다.")
	public Response createEstimate(@RequestBody @Valid CreateEstimateRequest request) {
		CreateEstimateResponse response = createEstimateUseCase.execute(request);
		return Response.createSuccessResponse(response);
	}

	@GetMapping("/estimates/{estimateId}")
	@Operation(summary = "견적서 ID로 견적서를 조회합니다.")
	public Response<GetEstimateResponse> getEstimate(@PathVariable UUID estimateId) {
		GetEstimateResponse response = getEstimateUseCase.execute(estimateId);
		return Response.createSuccessResponse(response);
	}
}
