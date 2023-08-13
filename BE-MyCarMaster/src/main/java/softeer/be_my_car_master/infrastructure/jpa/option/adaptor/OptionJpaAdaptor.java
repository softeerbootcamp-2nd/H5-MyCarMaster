package softeer.be_my_car_master.infrastructure.jpa.option.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;
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
	@Transactional(readOnly = true)
	public List<Option> findSelectableOptionsByTrimId(Long trimId) {
		List<TrimAdditionalOptionEntity> additionalTrimOptionEntities =
			trimAdditionalOptionJpaRepository.findAllByTrimId(trimId);

		loadTag(additionalTrimOptionEntities);

		return additionalTrimOptionEntities.stream()
			.map(TrimAdditionalOptionEntity::toOption)
			.collect(Collectors.toList());
	}

	private void loadTag(List<TrimAdditionalOptionEntity> additionalTrimOptionEntities) {
		additionalTrimOptionEntities.stream()
			.map(TrimAdditionalOptionEntity::getOption)
			.forEach(OptionEntity::getTag);
	}

	@Override
	public List<Long> findSelectableOptionIdsByTrimId(Long trimId) {
		return trimAdditionalOptionJpaRepository.findUnselectableOptionIdsByTrimId(trimId);
	}

	@Override
	public List<Option> findUnselectableOptions(Long trimId, List<Long> optionIds) {
		return trimAdditionalOptionJpaRepository.findAllByIdIn(trimId, optionIds).stream()
			.map(TrimAdditionalOptionEntity::toSimpleOption)
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
