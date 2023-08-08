package softeer.be_my_car_master.infrastructure.jpa.trim.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.trim.usecase.port.TrimPort;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;
import softeer.be_my_car_master.infrastructure.jpa.trim.repository.TrimJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class TrimJpaAdaptor implements TrimPort {

	private final TrimJpaRepository trimJpaRepository;

	@Override
	public List<Trim> findTrims(Long modelId) {
		return trimJpaRepository.findTrimsByModelId(modelId).stream()
			.map(TrimEntity::toTrim)
			.collect(Collectors.toList());
	}
}
