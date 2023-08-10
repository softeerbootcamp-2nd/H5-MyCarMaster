package softeer.be_my_car_master.api.engine.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.engine.Engine;

public interface EnginePort {
	List<Engine> findSelectableEnginesByTrimId(Long trimId);
}
