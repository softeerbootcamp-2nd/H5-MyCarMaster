package softeer.be_my_car_master.infrastructure.body_type;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.usecase.port.BodyTypePort;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.Adaptor;

@Adaptor
@RequiredArgsConstructor
public class BodyTypeJpaAdaptor implements BodyTypePort {

	private final BodyTypeJpaRepository bodyTypeJpaRepository;

	@Override
	public List<BodyType> findBodyTypes(Long modelId) {
		return bodyTypeJpaRepository.findBodyTypesByModelId(modelId).stream()
			.map(BodyTypeEntity::toBodyType)
			.collect(Collectors.toList());
	}
}
