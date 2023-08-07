package softeer.bemycarmaster.api.domain.engine.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.engine.dto.request.GetEnginesRequest;
import softeer.bemycarmaster.api.domain.engine.dto.request.GetUnselectableOptionsByEngineRequest;
import softeer.bemycarmaster.api.domain.engine.dto.response.GetEnginesResponse;
import softeer.bemycarmaster.api.domain.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.bemycarmaster.api.domain.engine.usecase.GetEnginesUseCase;
import softeer.bemycarmaster.api.domain.engine.usecase.GetUnselectableOptionsByEngineUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/engines")
@RequiredArgsConstructor
@Tag(name = "Engine", description = "Engine API Document")
public class EngineController {

	private final GetEnginesUseCase getEnginesUseCase;
	private final GetUnselectableOptionsByEngineUseCase getUnselectableOptionsByEngineUseCase;

	@GetMapping
	@Operation(summary = "트림에 따른 엔진 목록을 반환합니다")
	public Response<GetEnginesResponse> getEngines(@RequestBody @Valid GetEnginesRequest getEnginesRequest) {
		Long trimId = getEnginesRequest.getTrimId();
		GetEnginesResponse getEnginesResponse = getEnginesUseCase.execute(trimId);
		return Response.createSuccessResponse(getEnginesResponse);
	}

	@GetMapping("/{engineId}/unselectable-options")
	@Operation(summary = "엔진 변경 시도시 기존에 선택된 옵션들 중 변경하려는 엔진에서 선택 불가능한 옵션 목록을 반환합니다.")
	public Response<GetUnselectableOptionsByEngineResponse> getUnselectableOptionsByEngine(
		@PathVariable Long engineId,
		@RequestBody @Valid GetUnselectableOptionsByEngineRequest getUnselectableOptionsByEngineRequest
	) {
		Long trimId = getUnselectableOptionsByEngineRequest.getTrimId();
		List<Long> optionIds = getUnselectableOptionsByEngineRequest.getOptionsIds();
		GetUnselectableOptionsByEngineResponse getUnselectableOptionsByEngineResponse =
			getUnselectableOptionsByEngineUseCase.execute(engineId, trimId, optionIds);
		return Response.createSuccessResponse(getUnselectableOptionsByEngineResponse);
	}
}
