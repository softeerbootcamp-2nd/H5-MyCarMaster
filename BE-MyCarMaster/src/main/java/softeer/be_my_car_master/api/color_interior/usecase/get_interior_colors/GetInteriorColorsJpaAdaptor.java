package softeer.be_my_car_master.api.color_interior.usecase.get_interior_colors;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.TrimInteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.ExteriorUnselectableInteriorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.TrimInteriorColorJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetInteriorColorsJpaAdaptor implements GetInteriorColorsPort {

	private final TrimInteriorColorJpaRepository trimInteriorColorJpaRepository;
	private final ExteriorUnselectableInteriorJpaRepository exteriorUnselectableInteriorJpaRepository;

	@Override
	public List<InteriorColor> findInteriorColorsByTrim(Long trimId) {
		return trimInteriorColorJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimInteriorColorEntity::toInteriorColor)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findUnselectableInteriorColorIdsByExteriorColor(Long exteriorColorId) {
		return exteriorUnselectableInteriorJpaRepository
			.findUnselectableInteriorColorIdsByExteriorColorId(exteriorColorId);
	}
}

