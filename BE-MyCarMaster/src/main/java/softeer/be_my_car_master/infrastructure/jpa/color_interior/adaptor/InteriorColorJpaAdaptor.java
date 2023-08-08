package softeer.be_my_car_master.infrastructure.jpa.color_interior.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.color_interior.usecase.port.InteriorColorPort;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.TrimInteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.TrimInteriorColorJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class InteriorColorJpaAdaptor implements InteriorColorPort {

	private final TrimInteriorColorJpaRepository trimInteriorColorJpaRepository;

	@Override
	public List<InteriorColor> findSelectableInteriorColorsByTrimId(Long trimId) {
		return trimInteriorColorJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimInteriorColorEntity::toInteriorColor)
			.collect(Collectors.toList());
	}
}
