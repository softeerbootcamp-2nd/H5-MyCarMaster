package softeer.be_my_car_master.infrastructure.jpa.option.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.BodyTypeUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.EngineUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.InteriorColorUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimAdditionalOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.WheelDriveUnselectableOptionJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class OptionJpaAdaptor implements OptionPort {

	private final TrimAdditionalOptionJpaRepository trimAdditionalOptionJpaRepository;
	private final EngineUnselectableOptionJpaRepository engineUnselectableOptionJpaRepository;
	private final WheelDriveUnselectableOptionJpaRepository wheelDriveUnselectableOptionJpaRepository;
	private final BodyTypeUnselectableOptionJpaRepository bodyTypeUnselectableOptionJpaRepository;
	private final InteriorColorUnselectableOptionJpaRepository interiorColorUnselectableOptionJpaRepository;

	@Override
	public List<Option> findSelectableOptionsByTrimId(Long trimId) {
		List<TrimAdditionalOptionEntity> additionalTrimOptionEntities =
			trimAdditionalOptionJpaRepository.findAllByTrimId(trimId);

		// todo: 불필요해보이는 로직. 데이터 삽입후 테스트 필요
		// additionalTrimOptionEntities.stream()
		// 	.map(AdditionalTrimOptionEntity::getOption)
		// 	.forEach(OptionEntity::getSubOptions);

		return additionalTrimOptionEntities.stream()
			.map(TrimAdditionalOptionEntity::toOption)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findUnselectableOptionIdsByEngineId(Long engineId) {
		return engineUnselectableOptionJpaRepository.findUnselectableOptionIdsByEngineId(engineId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByWheelDriveId(Long wheelDriveId) {
		return wheelDriveUnselectableOptionJpaRepository.findUnselectableOptionIdsByWheelDriveId(wheelDriveId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByBodyTypeId(Long bodyTypeId) {
		return bodyTypeUnselectableOptionJpaRepository.findUnselectableOptionIdsByBodyTypeId(bodyTypeId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByInteriorColorId(Long interiorColorId) {
		return interiorColorUnselectableOptionJpaRepository.findUnselectableOptionIdsByInteriorColorId(interiorColorId);
	}
}
