package softeer.be_my_car_master.application.agency.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.agency.dto.request.GetAgenciesRequest;
import softeer.be_my_car_master.application.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.application.agency.usecase.get_agencies.GetAgenciesUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Agency", description = "Agency API Document")
public class AgencyController {

	private final GetAgenciesUseCase getAgenciesUseCase;

	@GetMapping("/agencies")
	public Response<GetAgenciesResponse> getAgencies(@Valid @ParameterObject GetAgenciesRequest request) {
		String gu = request.getGu();
		GetAgenciesResponse response = getAgenciesUseCase.execute(gu);
		return Response.createSuccessResponse(response);
	}
}
