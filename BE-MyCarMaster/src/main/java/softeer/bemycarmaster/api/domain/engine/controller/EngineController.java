package softeer.bemycarmaster.api.domain.engine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.engine.dto.request.GetEnginesRequest;
import softeer.bemycarmaster.api.domain.engine.dto.response.GetEnginesResponse;
import softeer.bemycarmaster.api.domain.engine.usecase.GetEnginesUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/engines")
@RequiredArgsConstructor
@Tag(name = "Engine", description = "Engine API Document")
public class EngineController {

	private final GetEnginesUseCase getEnginesUseCase;

	@GetMapping
	@Operation(summary = "모델과 트림에 따른 엔진 목록을 반환합니다")
	public Response<GetEnginesResponse> getEngines(@RequestBody GetEnginesRequest getEnginesRequest) {

		Integer modelId = getEnginesRequest.getModelId();
		Integer trimId = getEnginesRequest.getTrimId();
		GetEnginesResponse getEnginesResponse = getEnginesUseCase.execute(modelId, trimId);
		return new Response<>(getEnginesResponse);
	}
}
