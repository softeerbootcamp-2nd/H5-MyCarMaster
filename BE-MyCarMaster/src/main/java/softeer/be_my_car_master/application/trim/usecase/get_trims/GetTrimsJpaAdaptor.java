package softeer.be_my_car_master.application.trim.usecase.get_trims;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;
import softeer.be_my_car_master.infrastructure.jpa.trim.repository.TrimJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetTrimsJpaAdaptor implements GetTrimsPort {

	private final TrimJpaRepository trimJpaRepository;

	@Override
	public List<Trim> findTrimsByModel(Long modelId) {
		return trimJpaRepository.findAllByModelId(modelId).stream()
			.map(TrimEntity::toTrim)
			.collect(Collectors.toList());
	}
}
