package softeer.bemycarmaster.api.domain.bodytype.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.bodytype.dto.request.GetBodyTypesRequest;
import softeer.bemycarmaster.api.domain.bodytype.dto.response.GetBodyTypesResponse;
import softeer.bemycarmaster.api.domain.bodytype.usecase.GetBodyTypesUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/bodytypes")
@RequiredArgsConstructor
@Tag(name = "Bodytype", description = "Bodytype API Document")
public class BodyTypeController {

	private final GetBodyTypesUseCase getBodyTypesUseCase;

	@GetMapping
	@Operation(summary = "트림에 따른 바디타입 목록을 반환합니다")
	public Response<GetBodyTypesResponse> getBodytypes(@RequestBody @Valid GetBodyTypesRequest getBodytypesRequest) {

		Integer trimId = getBodytypesRequest.getTrimId();
		GetBodyTypesResponse getBodyTypesResponse = getBodyTypesUseCase.execute(trimId);
		return Response.createSuccessResponse(getBodyTypesResponse);
	}
}
