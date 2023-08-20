package softeer.be_my_car_master.api.option.controller;

import java.util.List;

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
import softeer.be_my_car_master.api.engine.dto.request.GetUnselectableOptionsByEngineRequest;
import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.api.engine.usecase.GetUnselectableOptionsByEngineUseCase;
import softeer.be_my_car_master.api.option.dto.request.GetDefaultOptionsRequest;
import softeer.be_my_car_master.api.option.dto.request.GetOptionsRequest;
import softeer.be_my_car_master.api.option.dto.request.GetRepresentativeOptionsRequest;
import softeer.be_my_car_master.api.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.api.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.GetDefaultOptionsUseCase;
import softeer.be_my_car_master.api.option.usecase.GetOptionsUseCase;
import softeer.be_my_car_master.api.option.usecase.GetRepresentativeOptionsUseCase;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.api.trim.usecase.GetTrimDefaultOptionsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Option", description = "Option API Document")
public class OptionController {

	private final GetOptionsUseCase getOptionsUseCase;
	private final GetUnselectableOptionsByEngineUseCase getUnselectableOptionsByEngineUseCase;

	@GetMapping("/options")
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

	@GetMapping("/engines/{engineId}/unselectable-options")
	@Operation(summary = "엔진 변경 시도시 기존에 선택된 옵션들 중 변경하려는 엔진에서 선택 불가능한 옵션 목록을 반환합니다.")
	public Response<GetUnselectableOptionsByEngineResponse> getUnselectableOptionsByEngine(
		@PathVariable Long engineId,
		@Valid @ParameterObject GetUnselectableOptionsByEngineRequest getUnselectableOptionsByEngineRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long trimId = getUnselectableOptionsByEngineRequest.getTrimId();
		List<Long> optionIds = getUnselectableOptionsByEngineRequest.getOptionIds();
		GetUnselectableOptionsByEngineResponse getUnselectableOptionsByEngineResponse =
			getUnselectableOptionsByEngineUseCase.execute(engineId, trimId, optionIds);
		return Response.createSuccessResponse(getUnselectableOptionsByEngineResponse);
	}


}
