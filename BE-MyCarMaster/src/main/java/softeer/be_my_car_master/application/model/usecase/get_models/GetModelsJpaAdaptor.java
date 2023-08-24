package softeer.be_my_car_master.application.model.usecase.get_models;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;
import softeer.be_my_car_master.infrastructure.jpa.model.repository.ModelJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetModelsJpaAdaptor implements GetModelsPort {

	private final ModelJpaRepository modelJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Model> findModels() {
		return modelJpaRepository.findAllByOrderByCreatedAtDesc().stream()
			.map(ModelEntity::toModel)
			.collect(Collectors.toList());
	}
}
