package softeer.be_my_car_master.application.trim.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.trim.dto.request.GetTrimsRequest;
import softeer.be_my_car_master.application.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.application.trim.usecase.get_trims.GetTrimsUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Trim", description = "Trim API Document")
public class TrimController {

	private final GetTrimsUseCase getTrimsUseCase;

	@GetMapping("/trims")
	@Operation(summary = "모델에 따른 트림 목록을 반환합니다")
	public Response<GetTrimsResponse> getTrims(
		@Valid @ParameterObject GetTrimsRequest request,
		BindingResult bindingResult
	) {
		Long modelId = request.getModelId();
		GetTrimsResponse getTrimsResponse = getTrimsUseCase.execute(modelId);
		return Response.createSuccessResponse(getTrimsResponse);
	}
}
