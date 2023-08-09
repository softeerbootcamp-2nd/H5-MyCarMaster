package softeer.be_my_car_master.api.body_type.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface BodyTypePort {

	List<BodyType> findSelectableBodyTypesByModelId(Long modelId);
}
