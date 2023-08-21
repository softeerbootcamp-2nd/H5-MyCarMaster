package softeer.be_my_car_master.application.engine.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.engine.dto.request.GetEnginesRequest;
import softeer.be_my_car_master.application.engine.dto.response.GetEnginesResponse;
import softeer.be_my_car_master.application.engine.usecase.get_engines.GetEnginesUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Engine", description = "Engine API Document")
public class EngineController {

	private final GetEnginesUseCase getEnginesUseCase;

	@GetMapping("/engines")
	@Operation(summary = "트림에 따른 엔진 목록을 반환합니다")
	public Response<GetEnginesResponse> getEngines(@Valid @ParameterObject GetEnginesRequest request) {
		Long trimId = request.getTrimId();
		GetEnginesResponse response = getEnginesUseCase.execute(trimId);
		return Response.createSuccessResponse(response);
	}
}
