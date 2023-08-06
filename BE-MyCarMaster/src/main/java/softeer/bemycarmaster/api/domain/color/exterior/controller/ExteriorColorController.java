package softeer.bemycarmaster.api.domain.color.exterior.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.color.exterior.dto.request.GetExteriorColorsRequest;
import softeer.bemycarmaster.api.domain.color.exterior.dto.response.GetExteriorColorsResponse;
import softeer.bemycarmaster.api.domain.color.exterior.usecase.GetExteriorColorsUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@Tag(name = "Exterior Color", description = "Exterior Color API Document")
public class ExteriorColorController {

	private final GetExteriorColorsUseCase getExteriorColorsUseCase;

	@GetMapping("/exterior")
	@Operation(summary = "트림에서 선택가능한 외장 색상 목록을 반환합니다")
	public Response<GetExteriorColorsResponse> getExteriorColors(
		@RequestBody @Valid GetExteriorColorsRequest getExteriorColorsRequest) {

		Integer trimId = getExteriorColorsRequest.getTrimId();
		GetExteriorColorsResponse getExteriorColorsResponse = getExteriorColorsUseCase.execute(trimId);
		return Response.createSuccessResponse(getExteriorColorsResponse);
	}
}
