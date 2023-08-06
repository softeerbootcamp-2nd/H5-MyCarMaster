package softeer.bemycarmaster.api.domain.wheeldrive.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.wheeldrive.dto.request.GetWheelDrivesRequest;
import softeer.bemycarmaster.api.domain.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.bemycarmaster.api.domain.wheeldrive.usecase.GetWheelDrivesUseCase;
import softeer.bemycarmaster.api.global.response.Response;

@RestController
@RequestMapping("/wheeldrives")
@RequiredArgsConstructor
@Tag(name = "Wheeldrive", description = "Wheeldrive API Document")
public class WheelDriveController {

	private final GetWheelDrivesUseCase getWheeldrivesUseCase;

	@GetMapping
	@Operation(summary = "트림에 따른 구동 방식 목록을 반환합니다")
	public Response<GetWheelDrivesResponse> getWheeldrives(
		@RequestBody @Valid GetWheelDrivesRequest getWheeldrivesRequest
	) {

		Integer trimId = getWheeldrivesRequest.getTrimId();
		GetWheelDrivesResponse getWheeldrivesResponse = getWheeldrivesUseCase.execute(trimId);
		return Response.createSuccessResponse(getWheeldrivesResponse);
	}
}
