package softeer.be_my_car_master.api.color_exterior.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.color_exterior.dto.request.GetExteriorColorsRequest;
import softeer.be_my_car_master.api.color_exterior.dto.response.GetExteriorColorsResponse;
import softeer.be_my_car_master.api.color_exterior.usecase.GetExteriorColorsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Exterior Color", description = "Exterior Color API Document")
public class ExteriorColorController {

	private final GetExteriorColorsUseCase getExteriorColorsUseCase;

	@GetMapping("/exterior-colors")
	@Operation(summary = "트림에서 선택가능한 외장 색상 목록을 반환합니다")
	public Response<GetExteriorColorsResponse> getExteriorColors(
		@Valid GetExteriorColorsRequest getExteriorColorsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long trimId = getExteriorColorsRequest.getTrimId();
		GetExteriorColorsResponse getExteriorColorsResponse = getExteriorColorsUseCase.execute(trimId);
		return Response.createSuccessResponse(getExteriorColorsResponse);
	}
}
