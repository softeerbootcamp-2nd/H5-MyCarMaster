package softeer.be_my_car_master.infrastructure.jpa.model.adaptor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.model.usecase.port.ModelPort;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;
import softeer.be_my_car_master.infrastructure.jpa.model.repository.ModelJpaRepository;

@Adaptor
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
