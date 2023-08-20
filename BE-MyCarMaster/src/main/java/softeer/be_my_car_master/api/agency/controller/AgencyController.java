package softeer.be_my_car_master.api.agency.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.request.GetAgenciesRequest;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.api.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.api.agency.usecase.GetCarMastersInAgencyUseCase;
import softeer.be_my_car_master.api.agency.usecase.get_agencies.GetAgenciesUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Agency", description = "Agency API Document")
public class AgencyController {

	private final GetAgenciesUseCase getAgenciesUseCase;
	private final GetCarMastersInAgencyUseCase getCarMastersInAgencyUseCase;

	@GetMapping("/agencies")
	public Response<GetAgenciesResponse> getAgencies(
		@Valid @ParameterObject GetAgenciesRequest request,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		String gu = request.getGu();
		GetAgenciesResponse response = getAgenciesUseCase.execute(gu);
		return Response.createSuccessResponse(response);
	}

	@GetMapping("/agencies/{agencyId}/car-masters")
	public Response<GetCarMastersInAgencyResponse> getCarMastersInAgency(
		@Min(value = 1, message = "agencyId는 1 이상의 값입니다.")
		@PathVariable Long agencyId
	) {
		GetCarMastersInAgencyResponse getCarMasterInAgencyResponse = getCarMastersInAgencyUseCase.execute(agencyId);
		return Response.createSuccessResponse(getCarMasterInAgencyResponse);
	}

}
