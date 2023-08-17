package softeer.be_my_car_master.api.agency.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.request.GetAgenciesRequest;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.api.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.api.agency.usecase.GetAgenciesUseCase;
import softeer.be_my_car_master.api.agency.usecase.GetCarMastersInAgencyUseCase;
import softeer.be_my_car_master.global.exception.BindingParamException;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/agencies")
@RequiredArgsConstructor
@Tag(name = "Agency", description = "Agency API Document")
public class AgencyController {

	private final GetAgenciesUseCase getAgenciesUseCase;
	private final GetCarMastersInAgencyUseCase getCarMastersInAgencyUseCase;

	@GetMapping
	public Response<GetAgenciesResponse> getAgencies(
		@Valid @ParameterObject GetAgenciesRequest getAgenciesRequest,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			throw new BindingParamException(bindingResult.getFieldErrors());
		}

		String gu = getAgenciesRequest.getGu();
		GetAgenciesResponse getAgenciesResponse = getAgenciesUseCase.execute(gu);
		return Response.createSuccessResponse(getAgenciesResponse);
	}

	@GetMapping("/{agencyId}/car-masters")
	public Response<GetCarMastersInAgencyResponse> getCarMastersInAgency(
		@Min(value = 1, message = "agencyId는 1 이상의 값입니다.")
		@PathVariable Long agencyId
	) {
		GetCarMastersInAgencyResponse getCarMasterInAgencyResponse = getCarMastersInAgencyUseCase.execute(agencyId);
		return Response.createSuccessResponse(getCarMasterInAgencyResponse);
	}

}
