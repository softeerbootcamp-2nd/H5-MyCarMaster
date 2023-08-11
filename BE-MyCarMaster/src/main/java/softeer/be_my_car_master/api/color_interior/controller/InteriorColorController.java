package softeer.be_my_car_master.api.color_interior.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.color_interior.dto.request.GetInteriorColorsRequest;
import softeer.be_my_car_master.api.color_interior.dto.response.GetInteriorColorsResponse;
import softeer.be_my_car_master.api.color_interior.usecase.GetInteriorColorsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Interior Color", description = "Interior Color API Document")
public class InteriorColorController {

	private final GetInteriorColorsUseCase getInteriorColorsUseCase;

	@GetMapping("/interior-colors")
	@Operation(summary = "트림, 외장 색상에서 선택가능한 내장 색상 목록을 반환합니다")
	public Response<GetInteriorColorsResponse> getInterior(
		@Valid @ParameterObject GetInteriorColorsRequest getInteriorColorsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long trimId = getInteriorColorsRequest.getTrimId();
		Long exteriorColorId = getInteriorColorsRequest.getExteriorColorId();
		GetInteriorColorsResponse getInteriorColorsResponse = getInteriorColorsUseCase.execute(trimId, exteriorColorId);
		return Response.createSuccessResponse(getInteriorColorsResponse);
	}
}
