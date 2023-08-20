package softeer.be_my_car_master.api.option.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.request.GetRepresentativeOptionsRequest;
import softeer.be_my_car_master.api.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.GetRepresentativeOptionsUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Representative Option", description = "Representative Option API Document")
public class RepresentativeOptionController {

	private final GetRepresentativeOptionsUseCase getRepresentativeOptionsUseCase;

	@GetMapping("/options/representative")
	@Operation(summary = "모델의 대표 옵션 9가지를 리턴합니다.")
	public Response<GetRepresentativeOptionsResponse> getRepresentativeOptions(
		@Valid @ParameterObject GetRepresentativeOptionsRequest getRepresentativeOptionsRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long modelId = getRepresentativeOptionsRequest.getModelId();
		GetRepresentativeOptionsResponse getRepresentativeOptionsResponse =
			getRepresentativeOptionsUseCase.execute(modelId);
		return Response.createSuccessResponse(getRepresentativeOptionsResponse);
	}
}
