package softeer.be_my_car_master.api.estimate.controller;

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
import softeer.be_my_car_master.api.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.api.estimate.dto.response.CreateEstimateResponse;
import softeer.be_my_car_master.api.estimate.dto.response.GetEstimateResponse;
import softeer.be_my_car_master.api.estimate.usecase.CreateEstimateUseCase;
import softeer.be_my_car_master.api.estimate.usecase.get_estimate.GetEstimateUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Estimate", description = "Estimate API Document")
public class EstimateController {

	private final CreateEstimateUseCase createEstimateUseCase;
	private final GetEstimateUseCase getEstimateUseCase;

	@PostMapping("/estimates")
	public Response createEstimate(@RequestBody @Valid CreateEstimateRequest createEstimateRequest) {
		CreateEstimateResponse createEstimateResponse = createEstimateUseCase.execute(createEstimateRequest);
		return Response.createSuccessResponse(createEstimateResponse);
	}

	@GetMapping("/estimates/{estimateId}")
	@Operation(summary = "견적서 조회")
	public Response<GetEstimateResponse> getEstimate(@PathVariable UUID estimateId) {
		GetEstimateResponse response = getEstimateUseCase.execute(estimateId);
		return Response.createSuccessResponse(response);
	}
}
