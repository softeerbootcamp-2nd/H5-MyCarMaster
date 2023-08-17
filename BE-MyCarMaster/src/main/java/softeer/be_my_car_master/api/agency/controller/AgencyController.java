package softeer.be_my_car_master.api.agency.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.request.GetAgenciesRequest;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.api.agency.usecase.GetAgenciesUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/agencies")
@RequiredArgsConstructor
@Tag(name = "Agency", description = "Agency API Document")
public class AgencyController {

	private final GetAgenciesUseCase getAgenciesUseCase;

	@GetMapping
	public Response<GetAgenciesResponse> getAgencies(@Valid @ParameterObject GetAgenciesRequest getAgenciesRequest) {
		String gu = getAgenciesRequest.getGu();
		GetAgenciesResponse getAgenciesResponse = getAgenciesUseCase.execute(gu);
		return Response.createSuccessResponse(getAgenciesResponse);
	}
}
