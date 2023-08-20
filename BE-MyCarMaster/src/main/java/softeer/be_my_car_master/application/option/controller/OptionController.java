package softeer.be_my_car_master.application.option.controller;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.engine.dto.request.GetUnselectableOptionsByEngineRequest;
import softeer.be_my_car_master.application.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.application.option.dto.request.GetOptionsRequest;
import softeer.be_my_car_master.application.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.application.option.usecase.get_options.GetOptionsUseCase;
import softeer.be_my_car_master.application.option.usecase.get_unselectable_options_by_engine.GetUnselectableOptionsByEngineUseCase;
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
		@Valid @ParameterObject GetOptionsRequest request,
		BindingResult bindingResult
	) {
		Long trimId = request.getTrimId();
		Long engineId = request.getEngineId();
		Long wheelDriveId = request.getWheelDriveId();
		Long bodyTypeId = request.getBodyTypeId();
		Long interiorColorId = request.getInteriorColorId();

		GetOptionsResponse response
			= getOptionsUseCase.execute(trimId, engineId, wheelDriveId, bodyTypeId, interiorColorId);
		return Response.createSuccessResponse(response);
	}

	@GetMapping("/engines/{engineId}/unselectable-options")
	@Operation(summary = "엔진 변경 시도시 기존에 선택된 옵션들 중 변경하려는 엔진에서 선택 불가능한 옵션 목록을 반환합니다.")
	public Response<GetUnselectableOptionsByEngineResponse> getUnselectableOptionsByEngine(
		@PathVariable Long engineId,
		@Valid @ParameterObject GetUnselectableOptionsByEngineRequest request,
		BindingResult bindingResult
	) {
		Long trimId = request.getTrimId();
		List<Long> optionIds = request.getOptionIds();

		GetUnselectableOptionsByEngineResponse response
			= getUnselectableOptionsByEngineUseCase.execute(engineId, trimId, optionIds);
		return Response.createSuccessResponse(response);
	}
}
