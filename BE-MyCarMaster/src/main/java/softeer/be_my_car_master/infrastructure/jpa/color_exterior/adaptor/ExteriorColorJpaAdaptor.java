package softeer.be_my_car_master.infrastructure.jpa.color_exterior.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.color_exterior.usecase.port.ExteriorColorPort;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.TrimExteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository.TrimExteriorColorJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class ExteriorColorJpaAdaptor implements ExteriorColorPort {

	private final TrimExteriorColorJpaRepository trimExteriorColorJpaRepository;

	@Override
	public List<ExteriorColor> findSelectableExteriorColorsByTrimId(Long trimId) {
		return trimExteriorColorJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimExteriorColorEntity::toExteriorColor)
			.collect(Collectors.toList());
	}
}
