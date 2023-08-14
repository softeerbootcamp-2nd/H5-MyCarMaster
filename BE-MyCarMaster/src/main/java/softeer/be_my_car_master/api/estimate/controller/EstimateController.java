package softeer.be_my_car_master.api.estimate.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.estimate.dto.request.MakeUpEstimateRequest;
import softeer.be_my_car_master.api.estimate.dto.response.MakeUpEstimateResponse;
import softeer.be_my_car_master.api.estimate.usecase.MakeUpEstimateUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Estimate", description = "Estimate API Document")
public class EstimateController {

	private final MakeUpEstimateUseCase makeUpEstimateUseCase;

	@PostMapping("/estimate")
	public Response makeUpEstimate(@RequestBody @Valid MakeUpEstimateRequest makeUpEstimateRequest) {
		MakeUpEstimateResponse makeUpEstimateResponse = makeUpEstimateUseCase.execute(makeUpEstimateRequest);
		return Response.createSuccessResponse(makeUpEstimateResponse);
	}
}
