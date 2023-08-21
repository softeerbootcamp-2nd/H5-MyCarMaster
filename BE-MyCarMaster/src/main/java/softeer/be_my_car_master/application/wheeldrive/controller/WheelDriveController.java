package softeer.be_my_car_master.application.wheeldrive.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.wheeldrive.dto.request.GetWheelDrivesRequest;
import softeer.be_my_car_master.application.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.be_my_car_master.application.wheeldrive.usecase.get_wheel_drives.GetWheelDrivesUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "WheelDrive", description = "WheelDrive API Document")
public class WheelDriveController {

	private final GetWheelDrivesUseCase getWheelDrivesUseCase;

	@GetMapping("/wheel-drives")
	@Operation(summary = "트림, 엔진에 따른 구동 방식 목록을 반환합니다")
	public Response<GetWheelDrivesResponse> getWheelDrives(@Valid @ParameterObject GetWheelDrivesRequest request) {
		Long trimId = request.getTrimId();
		Long engineId = request.getEngineId();
		GetWheelDrivesResponse response = getWheelDrivesUseCase.execute(trimId, engineId);
		return Response.createSuccessResponse(response);
	}
}
