package softeer.be_my_car_master.api.agency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.api.agency.usecase.GetAgenciesUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/agencies")
@RequiredArgsConstructor
@Tag(name = "Agency Color", description = "Agency API Document")
public class AgencyController {

	private final GetAgenciesUseCase getAgenciesUseCase;

	@GetMapping
	public Response<GetAgenciesResponse> getAgencies() {
		GetAgenciesResponse getAgenciesResponse = getAgenciesUseCase.execute();
		return Response.createSuccessResponse(getAgenciesResponse);
	}
}
