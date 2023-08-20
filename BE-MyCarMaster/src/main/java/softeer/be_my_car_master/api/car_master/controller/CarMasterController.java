package softeer.be_my_car_master.api.car_master.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.api.car_master.dto.request.GetCarMasterRequest;
import softeer.be_my_car_master.api.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.api.car_master.usecase.GetCarMastersInAgencyUseCase;
import softeer.be_my_car_master.api.car_master.usecase.get_car_masters.GetCarMasterUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "CarMaster", description = "CarMaster API Document")
public class CarMasterController {

	private final GetCarMasterUseCase getCarMasterUseCase;
	private final GetCarMastersInAgencyUseCase getCarMastersInAgencyUseCase;

	@GetMapping("/car-masters")
	@Operation(summary = "카마스터 찾기시 대리점과 카마스터 정보를 반환합니다.")
	public Response<GetCarMasterResponse> getCarMasters(
		@Valid @ParameterObject GetCarMasterRequest request,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		Double latitude = request.getLatitude();
		Double longitude = request.getLongitude();

		GetCarMasterResponse response = getCarMasterUseCase.execute(latitude, longitude);
		return Response.createSuccessResponse(response);
	}

	@GetMapping("/agencies/{agencyId}/car-masters")
	@Operation(summary = "특정 대리점에 속한 카마스터 목록을 반환합니다.")
	public Response<GetCarMastersInAgencyResponse> getCarMastersInAgency(
		@Min(value = 1, message = "agencyId는 1 이상의 값입니다.")
		@PathVariable Long agencyId
	) {
		GetCarMastersInAgencyResponse response = getCarMastersInAgencyUseCase.execute(agencyId);
		return Response.createSuccessResponse(response);
	}
}
