package softeer.be_my_car_master.application.color_exterior.usecase.get_exterior_colors;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.TrimExteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository.TrimExteriorColorJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetExteriorColorsJpaAdaptor implements GetExteriorColorsPort {

	private final TrimExteriorColorJpaRepository trimExteriorColorJpaRepository;

	@Override
	public List<ExteriorColor> findExteriorColorsByTrim(Long trimId) {
		return trimExteriorColorJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimExteriorColorEntity::toExteriorColor)
			.collect(Collectors.toList());
	}
}
