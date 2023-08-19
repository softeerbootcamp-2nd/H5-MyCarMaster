package softeer.be_my_car_master.api.body_type.usecase.get_body_types;

import java.util.List;

import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetBodyTypesPort {

	List<BodyType> findBodyTypesByModel(Long modelId);
}
