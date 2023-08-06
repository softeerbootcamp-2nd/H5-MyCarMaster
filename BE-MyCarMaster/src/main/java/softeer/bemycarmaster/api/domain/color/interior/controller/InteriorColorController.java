package softeer.bemycarmaster.api.domain.color.interior.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.color.interior.dto.request.GetInteriorColorsRequest;
import softeer.bemycarmaster.api.domain.color.interior.dto.response.GetInteriorColorsResponse;
import softeer.bemycarmaster.api.domain.color.interior.usecase.GetInteriorColorsUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@Tag(name = "Interior Color", description = "Interior Color API Document")
public class InteriorColorController {

	private final GetInteriorColorsUseCase getInteriorColorsUseCase;

	@GetMapping("/interior")
	@Operation(summary = "트림, 외장 색상에서 선택가능한 내장 색상 목록을 반환합니다")
	public Response<GetInteriorColorsResponse> getInterior(
		@RequestBody GetInteriorColorsRequest getInteriorColorsRequest) {

		Integer trimId = getInteriorColorsRequest.getTrimId();
		Integer exteriorColorId = getInteriorColorsRequest.getExteriorColorId();
		GetInteriorColorsResponse getInteriorColorsResponse = getInteriorColorsUseCase.execute(trimId, exteriorColorId);
		return Response.createSuccessResponse(getInteriorColorsResponse);
	}
}