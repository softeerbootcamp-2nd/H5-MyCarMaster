package softeer.be_my_car_master.application.estimate.usecase.create_estimate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.body_type.entity.BodyTypeEntity;
import softeer.be_my_car_master.infrastructure.jpa.body_type.repository.BodyTypeJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.ExteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.TrimExteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository.ExteriorColorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository.TrimExteriorColorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.InteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.TrimInteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.ExteriorUnselectableInteriorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.InteriorColorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.TrimInteriorColorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.engine.entity.EngineEntity;
import softeer.be_my_car_master.infrastructure.jpa.engine.entity.TrimEngineEntity;
import softeer.be_my_car_master.infrastructure.jpa.engine.repository.EngineJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.engine.repository.TrimEngineJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;
import softeer.be_my_car_master.infrastructure.jpa.estimate.repository.EstimateJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;
import softeer.be_my_car_master.infrastructure.jpa.model.repository.ModelJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.BodyTypeUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.EngineUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.InteriorColorUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.OptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimAdditionalOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.WheelDriveUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;
import softeer.be_my_car_master.infrastructure.jpa.trim.repository.TrimJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.TrimWheelDriveEntity;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.WheelDriveEntity;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository.EngineUnselectableWheelDriveJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository.TrimWheelDriveJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository.WheelDriveJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class CreateEstimateAdaptor implements CreateEstimatePort {

	private final EstimateJpaRepository estimateRepository;
	private final ModelJpaRepository modelJpaRepository;
	private final TrimJpaRepository trimJpaRepository;
	private final EngineJpaRepository engineJpaRepository;
	private final WheelDriveJpaRepository wheelDriveJpaRepository;
	private final BodyTypeJpaRepository bodyTypeJpaRepository;
	private final ExteriorColorJpaRepository exteriorColorJpaRepository;
	private final InteriorColorJpaRepository interiorColorJpaRepository;
	private final OptionJpaRepository optionJpaRepository;
	private final TrimEngineJpaRepository trimEngineJpaRepository;
	private final TrimWheelDriveJpaRepository trimWheelDriveJpaRepository;
	private final EngineUnselectableWheelDriveJpaRepository engineUnselectableWheelDriveJpaRepository;
	private final TrimExteriorColorJpaRepository trimExteriorColorJpaRepository;
	private final TrimInteriorColorJpaRepository trimInteriorColorJpaRepository;
	private final ExteriorUnselectableInteriorJpaRepository exteriorUnselectableInteriorJpaRepository;
	private final TrimAdditionalOptionJpaRepository trimAdditionalOptionJpaRepository;
	private final EngineUnselectableOptionJpaRepository engineUnselectableOptionJpaRepository;
	private final WheelDriveUnselectableOptionJpaRepository wheelDriveUnselectableOptionJpaRepository;
	private final BodyTypeUnselectableOptionJpaRepository bodyTypeUnselectableOptionJpaRepository;
	private final InteriorColorUnselectableOptionJpaRepository interiorColorUnselectableOptionJpaRepository;

	@Override
	public List<Model> findModels() {
		return modelJpaRepository.findAll().stream()
			.map(ModelEntity::toModel)
			.collect(Collectors.toList());
	}

	@Override
	public List<Trim> findTrimsByModel(Long modelId) {
		return trimJpaRepository.findAllByModelId(modelId).stream()
			.map(TrimEntity::toTrim)
			.collect(Collectors.toList());
	}

	@Override
	public List<Engine> findEnginesByTrim(Long trimId) {
		return trimEngineJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimEngineEntity::toEngine)
			.collect(Collectors.toList());
	}

	@Override
	public List<BodyType> findBodyTypesByModel(Long modelId) {
		return bodyTypeJpaRepository.findAllByModelId(modelId).stream()
			.map(BodyTypeEntity::toBodyType)
			.collect(Collectors.toList());
	}

	@Override
	public List<WheelDrive> findWheelDrivesByTrim(Long trimId) {
		return trimWheelDriveJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimWheelDriveEntity::toWheelDrive)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findUnselectableWheelDriveIdsByEngine(Long engineId) {
		return engineUnselectableWheelDriveJpaRepository
			.findUnselectableWheelDriveIdsByEngineId(engineId);
	}

	@Override
	public List<ExteriorColor> findExteriorColorsByTrim(Long trimId) {
		return trimExteriorColorJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimExteriorColorEntity::toExteriorColor)
			.collect(Collectors.toList());
	}

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
	public UUID createEstimate(
		Long modelId, Long trimId, Long engineId, Long wheelDriveId, Long bodyTypeId,
		Long exteriorColorId, Long interiorColorId, List<Long> selectedOptionIds, List<Long> considerOptionIds
	) {
		ModelEntity model = modelJpaRepository.findById(modelId).get();
		TrimEntity trim = trimJpaRepository.findById(trimId).get();
		EngineEntity engine = engineJpaRepository.findById(engineId).get();
		WheelDriveEntity wheelDrive = wheelDriveJpaRepository.findById(wheelDriveId).get();
		BodyTypeEntity bodyType = bodyTypeJpaRepository.findById(bodyTypeId).get();
		ExteriorColorEntity exteriorColor = exteriorColorJpaRepository.findById(exteriorColorId).get();
		InteriorColorEntity interiorColor = interiorColorJpaRepository.findById(interiorColorId).get();
		List<OptionEntity> selectOptions = optionJpaRepository.findAllByIdIn(selectedOptionIds);
		List<OptionEntity> considerOptions = optionJpaRepository.findAllByIdIn(considerOptionIds);

		EstimateEntity estimateEntity = EstimateEntity.create(
			model,
			trim,
			engine,
			wheelDrive,
			bodyType,
			exteriorColor,
			interiorColor,
			selectOptions,
			considerOptions
		);

		EstimateEntity savedEstimate = estimateRepository.save(estimateEntity);
		return savedEstimate.getUuid();
	}
}
