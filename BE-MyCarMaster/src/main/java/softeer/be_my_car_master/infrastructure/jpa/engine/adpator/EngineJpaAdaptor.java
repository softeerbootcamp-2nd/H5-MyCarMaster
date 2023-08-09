package softeer.be_my_car_master.infrastructure.jpa.engine.adpator;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.engine.usecase.port.EnginePort;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.engine.entity.TrimEngineEntity;
import softeer.be_my_car_master.infrastructure.jpa.engine.repository.TrimEngineJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class EngineJpaAdaptor implements EnginePort {

	private final TrimEngineJpaRepository trimEngineJpaRepository;

	@Override
	public List<Engine> findSelectableEnginesByTrimId(Long trimId) {
		return trimEngineJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimEngineEntity::toEngine)
			.collect(Collectors.toList());
	}
}
