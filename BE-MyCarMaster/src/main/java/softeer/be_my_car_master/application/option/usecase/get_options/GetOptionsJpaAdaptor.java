package softeer.be_my_car_master.application.option.usecase.get_options;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.BodyTypeUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.EngineUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.InteriorColorUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimAdditionalOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.WheelDriveUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.tag.repository.TagJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetOptionsJpaAdaptor implements GetOptionsPort {

	private final TrimAdditionalOptionJpaRepository trimAdditionalOptionJpaRepository;
	private final EngineUnselectableOptionJpaRepository engineUnselectableOptionJpaRepository;
	private final WheelDriveUnselectableOptionJpaRepository wheelDriveUnselectableOptionJpaRepository;
	private final BodyTypeUnselectableOptionJpaRepository bodyTypeUnselectableOptionJpaRepository;
	private final InteriorColorUnselectableOptionJpaRepository interiorColorUnselectableOptionJpaRepository;
	private final TagJpaRepository tagJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Option> findOptionsByTrim(Long trimId) {
		List<TrimAdditionalOptionEntity> additionalTrimOptionEntities =
			trimAdditionalOptionJpaRepository.findAllByTrimId(trimId);

		loadTag(additionalTrimOptionEntities);

		return additionalTrimOptionEntities.stream()
			.map(TrimAdditionalOptionEntity::toOption)
			.collect(Collectors.toList());
	}

	private void loadTag(List<TrimAdditionalOptionEntity> trimAdditionalOptionEntities) {
		trimAdditionalOptionEntities.stream()
			.map(TrimAdditionalOptionEntity::getOption)
			.forEach(OptionEntity::getTag);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByEngine(Long engineId) {
		return engineUnselectableOptionJpaRepository.findUnselectableOptionIdsByEngineId(engineId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByWheelDrive(Long wheelDriveId) {
		return wheelDriveUnselectableOptionJpaRepository.findUnselectableOptionIdsByWheelDriveId(wheelDriveId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByBodyType(Long bodyTypeId) {
		return bodyTypeUnselectableOptionJpaRepository.findUnselectableOptionIdsByBodyTypeId(bodyTypeId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByInteriorColor(Long interiorColorId) {
		return interiorColorUnselectableOptionJpaRepository.findUnselectableOptionIdsByInteriorColorId(interiorColorId);
	}

	@Override
	public List<String> findSingleSelectableTags() {
		return tagJpaRepository.findSingleSelectableTagIdsByIsMultiSelectable();
	}
}
