package softeer.bemycarmaster.api.domain.model.usecase.port;

import java.util.List;

import softeer.bemycarmaster.api.domain.model.domain.Model;

public interface ModelPort {

	List<Model> findModels();
}
