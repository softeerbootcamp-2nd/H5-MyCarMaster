package softeer.bemycarmaster.api.domain.bodytype.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.bodytype.dto.request.GetBodytypesRequest;
import softeer.bemycarmaster.api.domain.bodytype.dto.response.GetBodytypesResponse;
import softeer.bemycarmaster.api.domain.bodytype.usecase.GetBodytypesUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/bodytypes")
@RequiredArgsConstructor
@Tag(name = "Bodytype", description = "Bodytype API Document")
public class BodytypeController {

	private final GetBodytypesUseCase getBodytypesUseCase;

	@GetMapping
	@Operation(summary = "모델과 트림에 따른 바디타입 목록을 반환합니다")
	public Response<GetBodytypesResponse> getBodytypes(@RequestBody GetBodytypesRequest getBodytypesRequest) {

		Integer modelId = getBodytypesRequest.getModelId();
		Integer trimId = getBodytypesRequest.getTrimId();
		GetBodytypesResponse getBodytypesResponse = getBodytypesUseCase.execute(modelId, trimId);
		return new Response<>(getBodytypesResponse);
	}
}
