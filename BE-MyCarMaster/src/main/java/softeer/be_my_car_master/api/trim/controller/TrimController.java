package softeer.be_my_car_master.api.trim.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.trim.dto.request.GetTrimsRequest;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.api.trim.usecase.GetTrimDefaultOptionsUseCase;
import softeer.be_my_car_master.api.trim.usecase.GetTrimsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/trims")
@RequiredArgsConstructor
@Tag(name = "Trim", description = "Trim API Document")
public class TrimController {

	private final GetTrimsUseCase getTrimsUseCase;
	private final GetTrimDefaultOptionsUseCase getTrimDefaultOptionsUseCase;

	@GetMapping
	@Operation(summary = "모델에 따른 트림 목록을 반환합니다")
	public Response<GetTrimsResponse> getTrims(
		@Valid @ParameterObject GetTrimsRequest request,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long modelId = request.getModelId();
		GetTrimsResponse getTrimsResponse = getTrimsUseCase.execute(modelId);
		return Response.createSuccessResponse(getTrimsResponse);
	}

	@GetMapping("/{trimId}/default-options")
	@Operation(summary = "트림 상세 정보 조회 - 해당 트림의 기본옵션 목록을 반환합니다.")
	public Response<GetTrimDefaultOptionsResponse> getTrimDefaultOptions(@PathVariable Long trimId) {

		GetTrimDefaultOptionsResponse getTrimDefaultOptionsResponse = getTrimDefaultOptionsUseCase.execute(trimId);
		return Response.createSuccessResponse(getTrimDefaultOptionsResponse);
	}
}
