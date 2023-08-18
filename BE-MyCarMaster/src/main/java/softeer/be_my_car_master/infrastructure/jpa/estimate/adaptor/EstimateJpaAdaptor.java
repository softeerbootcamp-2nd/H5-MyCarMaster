package softeer.be_my_car_master.infrastructure.jpa.estimate.adaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.api.estimate.dto.request.EstimateOptionDto;
import softeer.be_my_car_master.api.estimate.usecase.port.EstimatePort;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.body_type.entity.BodyTypeEntity;
import softeer.be_my_car_master.infrastructure.jpa.body_type.repository.BodyTypeJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.ExteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository.ExteriorColorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.InteriorColorEntity;
import softeer.be_my_car_master.infrastructure.jpa.color_interior.repository.InteriorColorJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.engine.entity.EngineEntity;
import softeer.be_my_car_master.infrastructure.jpa.engine.repository.EngineJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;
import softeer.be_my_car_master.infrastructure.jpa.estimate.repository.EstimateJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;
import softeer.be_my_car_master.infrastructure.jpa.model.repository.ModelJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.OptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;
import softeer.be_my_car_master.infrastructure.jpa.trim.repository.TrimJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.WheelDriveEntity;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository.WheelDriveJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class EstimateJpaAdaptor implements EstimatePort {

	private final EstimateJpaRepository estimateRepository;
	private final ModelJpaRepository modelJpaRepository;
	private final TrimJpaRepository trimJpaRepository;
	private final EngineJpaRepository engineJpaRepository;
	private final WheelDriveJpaRepository wheelDriveJpaRepository;
	private final BodyTypeJpaRepository bodyTypeJpaRepository;
	private final ExteriorColorJpaRepository exteriorColorJpaRepository;
	private final InteriorColorJpaRepository interiorColorJpaRepository;
	private final OptionJpaRepository optionJpaRepository;

	@Override
	public UUID createEstimate(CreateEstimateRequest createEstimateRequest) {
		ModelEntity model = modelJpaRepository.findById(createEstimateRequest.getModelId()).get();
		TrimEntity trim = trimJpaRepository.findById(createEstimateRequest.getTrimId()).get();
		EngineEntity engine = engineJpaRepository.findById(createEstimateRequest.getEngineId()).get();
		WheelDriveEntity wheelDrive = wheelDriveJpaRepository.findById(createEstimateRequest.getWheelDriveId()).get();
		BodyTypeEntity bodyType = bodyTypeJpaRepository.findById(createEstimateRequest.getBodyTypeId()).get();
		ExteriorColorEntity exteriorColor =
			exteriorColorJpaRepository.findById(createEstimateRequest.getExteriorColorId()).get();
		InteriorColorEntity interiorColor =
			interiorColorJpaRepository.findById(createEstimateRequest.getInteriorColorId()).get();

		List<Long> selectOptionIds = createEstimateRequest.getSelectOptions().stream()
			.map(EstimateOptionDto::getId)
			.collect(Collectors.toList());
		List<OptionEntity> selectOptions = optionJpaRepository.findAllByIdIn(selectOptionIds);

		List<Long> considerOptionIds = createEstimateRequest.getConsiderOptions().stream()
			.map(EstimateOptionDto::getId)
			.collect(Collectors.toList());
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

	@Override
	public Optional<Estimate> findByUuid(UUID estimateUuid) {
		return estimateRepository.findByUuid(estimateUuid)
			.map(EstimateEntity::toSimpleEstimate);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Estimate> findFullEstimateByUuid(UUID estimateUuid) {
		return estimateRepository.findFullEstimateByUuid(estimateUuid)
			.map(EstimateEntity::toEstimate);
	}
}
