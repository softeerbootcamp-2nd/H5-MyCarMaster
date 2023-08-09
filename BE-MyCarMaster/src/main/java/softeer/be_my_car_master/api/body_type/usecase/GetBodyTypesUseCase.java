package softeer.be_my_car_master.api.body_type.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.dto.response.GetBodyTypesResponse;
import softeer.be_my_car_master.api.body_type.usecase.port.BodyTypePort;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetBodyTypesUseCase {

	private final BodyTypePort bodyTypePort;

	public GetBodyTypesResponse execute(Long modelId) {
		List<BodyType> selectableBodyTypes = bodyTypePort.findSelectableBodyTypesByModelId(modelId);
		return GetBodyTypesResponse.from(selectableBodyTypes);
	}
}
