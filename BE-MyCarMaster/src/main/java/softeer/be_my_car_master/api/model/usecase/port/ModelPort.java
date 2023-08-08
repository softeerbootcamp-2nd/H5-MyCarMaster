package softeer.be_my_car_master.api.model.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface ModelPort {

	List<Model> findModels();
}
