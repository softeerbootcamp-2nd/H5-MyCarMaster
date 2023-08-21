package softeer.be_my_car_master.application.option.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.option.dto.request.GetDefaultOptionsRequest;
import softeer.be_my_car_master.application.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.application.option.usecase.get_default_options.GetDefaultOptionsUseCase;
import softeer.be_my_car_master.application.option.usecase.get_trim_default_options.GetTrimDefaultOptionsUseCase;
import softeer.be_my_car_master.application.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.global.response.Response;

@RestController
@RequiredArgsConstructor
@Tag(name = "Default Option", description = "Default Option API Document")
public class DefaultOptionController {

	private final GetTrimDefaultOptionsUseCase getTrimDefaultOptionsUseCase;
	private final GetDefaultOptionsUseCase getDefaultOptionsUseCase;

	@GetMapping("/options/default")
	@Operation(summary = "트림, 엔진, 구동 방식, 바디 타입에 해당하는 기본 옵션 목록을 반환합니다")
	public Response<GetDefaultOptionsResponse> getDefaultOptions(
		@Valid @ParameterObject GetDefaultOptionsRequest request,
		BindingResult bindingResult
	) {
		Long trimId = request.getTrimId();
		Long engineId = request.getEngineId();
		Long wheelDriveId = request.getWheelDriveId();
		Long bodyTypeId = request.getBodyTypeId();

		GetDefaultOptionsResponse response = getDefaultOptionsUseCase.execute(
			trimId,
			engineId,
			wheelDriveId,
			bodyTypeId
		);
		return Response.createSuccessResponse(response);
	}

	@GetMapping("/trims/{trimId}/default-options")
	@Operation(summary = "트림 상세 정보 조회 - 해당 트림의 기본옵션 목록을 반환합니다.")
	public Response<GetTrimDefaultOptionsResponse> getTrimDefaultOptions(@PathVariable Long trimId) {
		GetTrimDefaultOptionsResponse response = getTrimDefaultOptionsUseCase.execute(trimId);
		return Response.createSuccessResponse(response);
	}

}
