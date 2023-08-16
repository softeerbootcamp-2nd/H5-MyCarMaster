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
import softeer.be_my_car_master.api.option.dto.request.GetDefaultOptionsRequest;
import softeer.be_my_car_master.api.option.dto.request.GetOptionsRequest;
import softeer.be_my_car_master.api.option.dto.request.GetRepresentativeOptionsRequest;
import softeer.be_my_car_master.api.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.GetDefaultOptionsUseCase;
import softeer.be_my_car_master.api.option.usecase.GetOptionsUseCase;
import softeer.be_my_car_master.api.option.usecase.GetRepresentativeOptionsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
@Tag(name = "Option", description = "Option API Document")
public class OptionController {

	private final GetOptionsUseCase getOptionsUseCase;
	private final GetDefaultOptionsUseCase getDefaultOptionsUseCase;
	private final GetRepresentativeOptionsUseCase getRepresentativeOptionsUseCase;

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

	@GetMapping("/default")
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

	@GetMapping("/representative")
	@Operation(summary = "모델의 대표 옵션 9가지를 리턴합니다.")
	public Response<GetRepresentativeOptionsResponse> getRepresentativeOptions(
		@Valid @ParameterObject GetRepresentativeOptionsRequest getRepresentativeOptionsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long modelId = getRepresentativeOptionsRequest.getModelId();
		GetRepresentativeOptionsResponse getRepresentativeOptionsResponse =
			getRepresentativeOptionsUseCase.execute(modelId);
		return Response.createSuccessResponse(getRepresentativeOptionsResponse);
	}
}
