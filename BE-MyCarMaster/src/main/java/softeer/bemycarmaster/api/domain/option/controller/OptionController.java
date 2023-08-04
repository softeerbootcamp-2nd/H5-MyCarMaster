package softeer.bemycarmaster.api.domain.option.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.option.dto.request.GetOptionsRequest;
import softeer.bemycarmaster.api.domain.option.dto.response.GetOptionsResponse;
import softeer.bemycarmaster.api.domain.option.usecase.GetOptionsUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
@Tag(name = "Option", description = "Option API Document")
public class OptionController {

	private final GetOptionsUseCase getOptionsUseCase;

	@GetMapping
	@Operation(summary = "모델, 트림, 엔진에서 선택 가능한 옵션 목록을 반환합니다")
	public Response<GetOptionsResponse> getOptions(@RequestBody GetOptionsRequest getOptionsRequest) {

		Integer modelId = getOptionsRequest.getModelId();
		Integer trimId = getOptionsRequest.getTrimId();
		Integer engineId = getOptionsRequest.getEngineId();
		GetOptionsResponse getOptionsResponse = getOptionsUseCase.execute(modelId, trimId, engineId);
		return new Response<>(getOptionsResponse);
	}
}
