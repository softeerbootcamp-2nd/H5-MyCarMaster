package softeer.bemycarmaster.api.domain.color.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.color.dto.request.GetExteriorColorsRequest;
import softeer.bemycarmaster.api.domain.color.dto.request.GetInteriorColorsRequest;
import softeer.bemycarmaster.api.domain.color.dto.response.GetExteriorColorsResponse;
import softeer.bemycarmaster.api.domain.color.dto.response.GetInteriorColorsResponse;
import softeer.bemycarmaster.api.domain.color.usecase.GetExteriorColorsUseCase;
import softeer.bemycarmaster.api.domain.color.usecase.GetInteriorColorsUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@Tag(name = "Color", description = "Color API Document")
public class ColorController {

	private final GetInteriorColorsUseCase getInteriorColorsUseCase;

	private final GetExteriorColorsUseCase getExteriorColorsUseCase;

	@GetMapping("/interior")
	@Operation(summary = "모델, 트림에서 선택가능한 내장 색상 목록을 반환합니다")
	public Response<GetInteriorColorsResponse> getInterior(
		@RequestBody GetInteriorColorsRequest getInteriorColorsRequest) {

		Integer modelId = getInteriorColorsRequest.getModelId();
		Integer trimId = getInteriorColorsRequest.getTrimId();
		GetInteriorColorsResponse getInteriorColorsResponse = getInteriorColorsUseCase.execute(modelId, trimId);
		return new Response<>(getInteriorColorsResponse);
	}

	@GetMapping("/exterior")
	@Operation(summary = "모델, 트림에서 선택가능한 외장 색상 목록을 반환합니다")
	public Response<GetExteriorColorsResponse> getInterior(
		@RequestBody GetExteriorColorsRequest getExteriorColorsRequest) {

		Integer modelId = getExteriorColorsRequest.getModelId();
		Integer trimId = getExteriorColorsRequest.getTrimId();
		GetExteriorColorsResponse getExteriorColorsResponse = getExteriorColorsUseCase.execute(modelId, trimId);
		return new Response<>(getExteriorColorsResponse);
	}
}
