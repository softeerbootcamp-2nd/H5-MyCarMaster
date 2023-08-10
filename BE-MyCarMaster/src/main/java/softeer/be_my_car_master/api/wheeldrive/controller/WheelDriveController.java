package softeer.be_my_car_master.api.wheeldrive.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.wheeldrive.dto.request.GetWheelDrivesRequest;
import softeer.be_my_car_master.api.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.be_my_car_master.api.wheeldrive.usecase.GetWheelDrivesUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "WheelDrive", description = "WheelDrive API Document")
public class WheelDriveController {

	private final GetWheelDrivesUseCase getWheelDrivesUseCase;

	@GetMapping("/wheel-drives")
	@Operation(summary = "트림, 엔진에 따른 구동 방식 목록을 반환합니다")
	public Response<GetWheelDrivesResponse> getWheelDrives(@Valid GetWheelDrivesRequest getWheelDrivesRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Long trimId = getWheelDrivesRequest.getTrimId();
		Long engineId = getWheelDrivesRequest.getEngineId();
		GetWheelDrivesResponse getWheelDrivesResponse = getWheelDrivesUseCase.execute(trimId, engineId);
		return Response.createSuccessResponse(getWheelDrivesResponse);
	}
}
