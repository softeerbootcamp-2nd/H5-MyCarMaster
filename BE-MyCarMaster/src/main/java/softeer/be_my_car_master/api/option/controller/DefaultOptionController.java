package softeer.be_my_car_master.api.option.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.request.GetDefaultOptionsRequest;
import softeer.be_my_car_master.api.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.GetDefaultOptionsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Default Option", description = "Default Option API Document")
public class DefaultOptionController {

	private final GetDefaultOptionsUseCase getDefaultOptionsUseCase;

	@GetMapping("/options/default")
	@Operation(summary = "트림, 엔진, 구동 방식, 바디 타입에 해당하는 기본 옵션 목록을 반환합니다")
	public Response<GetDefaultOptionsResponse> getDefaultOptions(
		@Valid @ParameterObject GetDefaultOptionsRequest getDefaultOptionsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long trimId = getDefaultOptionsRequest.getTrimId();
		Long engineId = getDefaultOptionsRequest.getEngineId();
		Long wheelDriveId = getDefaultOptionsRequest.getWheelDriveId();
		Long bodyTypeId = getDefaultOptionsRequest.getBodyTypeId();

		GetDefaultOptionsResponse getDefaultOptionsResponse = getDefaultOptionsUseCase.execute(
			trimId,
			engineId,
			wheelDriveId,
			bodyTypeId
		);
		return Response.createSuccessResponse(getDefaultOptionsResponse);
	}
}
