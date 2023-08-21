package softeer.be_my_car_master.application.model.usecase.get_models;

import java.util.List;

import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetModelsPort {

	List<Model> findModels();
}
