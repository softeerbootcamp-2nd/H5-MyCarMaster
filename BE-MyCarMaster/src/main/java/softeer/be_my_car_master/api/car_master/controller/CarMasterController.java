package softeer.be_my_car_master.api.car_master.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.car_master.dto.request.FilterEnum;
import softeer.be_my_car_master.api.car_master.dto.request.GetCarMasterRequest;
import softeer.be_my_car_master.api.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.api.car_master.usecase.GetCarMasterUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/car-masters")
@RequiredArgsConstructor
@Tag(name = "Exterior Color", description = "Exterior Color API Document")
public class CarMasterController {

	private final GetCarMasterUseCase getCarMasterUseCase;

	@GetMapping
	public Response<GetCarMasterResponse> getCarMaster(@Valid GetCarMasterRequest getCarMasterRequest) {
		Double latitude = getCarMasterRequest.getLatitude();
		Double longitude = getCarMasterRequest.getLongitude();
		FilterEnum filter = getCarMasterRequest.getFilter();

		GetCarMasterResponse getCarMasterResponse = getCarMasterUseCase.execute(latitude, longitude, filter.name());
		return Response.createSuccessResponse(getCarMasterResponse);
	}
}
