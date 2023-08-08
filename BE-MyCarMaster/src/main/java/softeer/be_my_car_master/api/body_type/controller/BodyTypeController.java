package softeer.be_my_car_master.api.body_type.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.dto.request.GetBodyTypesRequest;
import softeer.be_my_car_master.api.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.api.body_type.usecase.GetBodyTypesUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "BodyType", description = "BodyType API Document")
public class BodyTypeController {

	private final GetBodyTypesUseCase getBodyTypesUseCase;

	@GetMapping("/body-types")
	@Operation(summary = "트림에 따른 바디타입 목록을 반환합니다")
	public Response<GetBodyTypesResponse> getBodyTypes(@RequestBody @Valid GetBodyTypesRequest getBodytypesRequest) {

		Integer trimId = getBodytypesRequest.getTrimId();
		GetBodyTypesResponse getBodyTypesResponse = getBodyTypesUseCase.execute(trimId);
		return Response.createSuccessResponse(getBodyTypesResponse);
	}
}
