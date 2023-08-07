package softeer.bemycarmaster.api.domain.trim.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.trim.dto.request.GetTrimsRequest;
import softeer.bemycarmaster.api.domain.trim.dto.response.GetTrimsResponse;
import softeer.bemycarmaster.api.domain.trim.usecase.GetTrimsUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/trims")
@RequiredArgsConstructor
@Tag(name = "Trim", description = "Trim API Document")
public class TrimController {

	private final GetTrimsUseCase getTrimsUseCase;

	@GetMapping
	@Operation(summary = "모델에 따른 트림 목록을 반환합니다")
	public Response<GetTrimsResponse> getTrims(@RequestBody @Valid GetTrimsRequest getTrimsRequest) {
		Long modelId = getTrimsRequest.getModelId();
		GetTrimsResponse getTrimsResponse = getTrimsUseCase.execute(modelId);
		return Response.createSuccessResponse(getTrimsResponse);
	}
}
