package softeer.be_my_car_master.api.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.model.dto.response.GetModelsResponse;
import softeer.be_my_car_master.api.model.usecase.GetModelsUseCase;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
@Tag(name = "Model", description = "Model API Document")
public class ModelController {

	private final GetModelsUseCase getModelsUseCase;

	@GetMapping
	@Operation(summary = "모델 전체 목록을 반환합니다")
	public Response<GetModelsResponse> getModels() {
		GetModelsResponse getModelsResponse = getModelsUseCase.execute();
		return new Response<>(getModelsResponse);
	}
}
