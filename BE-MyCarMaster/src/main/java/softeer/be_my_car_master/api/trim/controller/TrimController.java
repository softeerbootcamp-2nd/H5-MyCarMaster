package softeer.be_my_car_master.api.trim.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.trim.dto.request.GetTrimsRequest;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.api.trim.usecase.GetTrimsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/trims")
@RequiredArgsConstructor
@Tag(name = "Trim", description = "Trim API Document")
public class TrimController {

	private final GetTrimsUseCase getTrimsUseCase;

	@GetMapping
	@Operation(summary = "모델에 따른 트림 목록을 반환합니다")
	public Response<GetTrimsResponse> getTrims(
		@Valid @ParameterObject GetTrimsRequest getTrimsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long modelId = getTrimsRequest.getModelId();
		GetTrimsResponse getTrimsResponse = getTrimsUseCase.execute(modelId);
		return Response.createSuccessResponse(getTrimsResponse);
	}
}
