package softeer.be_my_car_master.api.engine.usecase.get_engines;

import java.util.List;

import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetEnginesPort {

	List<Engine> findEnginesByTrim(Long trimId);
}
