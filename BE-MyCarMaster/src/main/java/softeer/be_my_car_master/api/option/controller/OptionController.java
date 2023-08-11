package softeer.be_my_car_master.api.option.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.request.GetOptionsRequest;
import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.GetOptionsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
@Tag(name = "Option", description = "Option API Document")
public class OptionController {

	private final GetOptionsUseCase getOptionsUseCase;

	@GetMapping
	@Operation(summary = "트림, 엔진, 구동 방식, 바디 타입, 내장 색상에서 선택 가능한 옵션 목록을 반환합니다")
	public Response<GetOptionsResponse> getOptions(
		@Valid @ParameterObject GetOptionsRequest getOptionsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long trimId = getOptionsRequest.getTrimId();
		Long engineId = getOptionsRequest.getEngineId();
		Long wheelDriveId = getOptionsRequest.getWheelDriveId();
		Long bodyTypeId = getOptionsRequest.getBodyTypeId();
		Long interiorColorId = getOptionsRequest.getInteriorColorId();

		GetOptionsResponse getOptionsResponse = getOptionsUseCase.execute(
			trimId,
			engineId,
			wheelDriveId,
			bodyTypeId,
			interiorColorId
		);
		return Response.createSuccessResponse(getOptionsResponse);
	}
}
