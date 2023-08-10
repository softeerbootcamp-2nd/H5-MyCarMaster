package softeer.be_my_car_master.infrastructure.jpa.body_type.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.usecase.port.BodyTypePort;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.body_type.entity.BodyTypeEntity;
import softeer.be_my_car_master.infrastructure.jpa.body_type.repository.BodyTypeJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class BodyTypeJpaAdaptor implements BodyTypePort {

	private final BodyTypeJpaRepository bodyTypeJpaRepository;

	@Override
	public List<BodyType> findSelectableBodyTypesByModelId(Long modelId) {
		return bodyTypeJpaRepository.findAllByModelId(modelId).stream()
			.map(BodyTypeEntity::toBodyType)
			.collect(Collectors.toList());
	}
}
