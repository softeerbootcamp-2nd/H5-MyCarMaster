package softeer.be_my_car_master.application.body_type.usecase.get_body_types;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetBodyTypesUseCase {

	private final GetBodyTypesPort port;

	@Cacheable(value = "get_body_types", key = "'modelId = ' + #modelId", unless = "#result == null")
	public GetBodyTypesResponse execute(Long modelId) {
		List<BodyType> bodyTypes = port.findBodyTypesByModel(modelId);
		return GetBodyTypesResponse.from(bodyTypes);
	}
}
