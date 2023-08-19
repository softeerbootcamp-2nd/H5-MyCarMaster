package softeer.be_my_car_master.api.body_type.usecase.get_body_types;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetBodyTypesUseCase {

	private final GetBodyTypesPort getBodyTypePort;

	public GetBodyTypesResponse execute(Long modelId) {
		List<BodyType> bodyTypes = getBodyTypePort.findBodyTypesByModel(modelId);
		return GetBodyTypesResponse.from(bodyTypes);
	}
}
