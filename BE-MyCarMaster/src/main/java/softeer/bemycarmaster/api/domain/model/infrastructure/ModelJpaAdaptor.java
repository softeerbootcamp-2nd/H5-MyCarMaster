package softeer.bemycarmaster.api.domain.model.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.model.domain.Model;
import softeer.bemycarmaster.api.domain.model.usecase.port.ModelPort;

@Repository
@RequiredArgsConstructor
public class ModelJpaAdaptor implements ModelPort {

	private final ModelJpaRepository modelJpaRepository;

	@Override
	public List<Model> findModels() {
		return modelJpaRepository.findAll().stream()
			.map(ModelEntity::toModel)
			.collect(Collectors.toList());
	}
}
