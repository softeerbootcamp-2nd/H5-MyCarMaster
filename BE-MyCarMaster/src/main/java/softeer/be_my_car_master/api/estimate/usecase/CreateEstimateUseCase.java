package softeer.be_my_car_master.api.estimate.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.usecase.port.BodyTypePort;
import softeer.be_my_car_master.api.color_exterior.usecase.port.ExteriorColorPort;
import softeer.be_my_car_master.api.color_interior.usecase.port.InteriorColorPort;
import softeer.be_my_car_master.api.engine.usecase.port.EnginePort;
import softeer.be_my_car_master.api.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.api.estimate.dto.request.EstimateOptionDto;
import softeer.be_my_car_master.api.estimate.dto.response.CreateEstimateResponse;
import softeer.be_my_car_master.api.estimate.exception.InvalidEstimationException;
import softeer.be_my_car_master.api.estimate.usecase.port.EstimatePort;
import softeer.be_my_car_master.api.model.usecase.port.ModelPort;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.api.trim.usecase.port.TrimPort;
import softeer.be_my_car_master.api.wheeldrive.usecase.port.WheelDrivePort;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class CreateEstimateUseCase {

	private final ModelPort modelPort;
	private final TrimPort trimPort;
	private final EnginePort enginePort;
	private final BodyTypePort bodyTypePort;
	private final WheelDrivePort wheelDrivePort;
	private final ExteriorColorPort exteriorColorPort;
	private final InteriorColorPort interiorColorPort;
	private final OptionPort optionPort;
	private final EstimatePort estimatePort;

	@Transactional
	public CreateEstimateResponse execute(CreateEstimateRequest createEstimateRequest) {
		Long modelId = createEstimateRequest.getModelId();
		List<Model> models = modelPort.findModels();
		validateModel(modelId, models);

		List<Trim> trims = trimPort.findTrimsByModel(modelId);
		validateTrim(createEstimateRequest, trims);

		List<Engine> engines = enginePort.findSelectableEnginesByTrimId(createEstimateRequest.getTrimId());
		validateEngine(createEstimateRequest, engines);

		List<BodyType> bodyTypes = bodyTypePort.findSelectableBodyTypesByModelId(modelId);
		validateBodyType(createEstimateRequest, bodyTypes);

		List<WheelDrive> wheelDrives =
			wheelDrivePort.findSelectableWheelDrivesByTrimId(createEstimateRequest.getTrimId());
		List<Long> unselectableWheelDriveIds =
			wheelDrivePort.findUnselectableWheelDriveIdsByEngineId(createEstimateRequest.getEngineId());
		List<WheelDrive> selectableWheelDrives = wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isSelectable(unselectableWheelDriveIds))
			.collect(Collectors.toList());
		validateWheelDrive(createEstimateRequest, selectableWheelDrives);

		List<ExteriorColor> exteriorColors =
			exteriorColorPort.findSelectableExteriorColorsByTrimId(createEstimateRequest.getTrimId());
		validateExteriorColor(createEstimateRequest, exteriorColors);

		List<InteriorColor> interiorColors =
			interiorColorPort.findSelectableInteriorColorsByTrimId(createEstimateRequest.getTrimId());
		List<Long> unselectableInteriorColorIds =
			interiorColorPort.findUnselectableInteriorColorIdsByExteriorColorId(
				createEstimateRequest.getExteriorColorId());
		List<InteriorColor> selectableInteriorColors = interiorColors.stream()
			.filter(interiorColor -> interiorColor.isSelectable(unselectableInteriorColorIds))
			.collect(Collectors.toList());
		validateInteriorColor(createEstimateRequest, selectableInteriorColors);

		List<Option> selectableOptions = optionPort.findSelectableOptionsByTrimId(createEstimateRequest.getTrimId());
		List<Long> unselectableOptionIdsByEngine
			= optionPort.findUnselectableOptionIdsByEngineId(createEstimateRequest.getEngineId());
		List<Long> unselectableOptionIdsByWheelDrive
			= optionPort.findUnselectableOptionIdsByWheelDriveId(createEstimateRequest.getWheelDriveId());
		List<Long> unselectableOptionIdsByBodyType
			= optionPort.findUnselectableOptionIdsByBodyTypeId(createEstimateRequest.getBodyTypeId());
		List<Long> unselectableOptionIdsByInteriorColor =
			optionPort.findUnselectableOptionIdsByInteriorColorId(createEstimateRequest.getInteriorColorId());

		Set<Long> unselectableOptionIdsSet = combineUnselectableOptionIds(
			unselectableOptionIdsByEngine,
			unselectableOptionIdsByWheelDrive,
			unselectableOptionIdsByBodyType,
			unselectableOptionIdsByInteriorColor
		);

		List<Option> filteredSelectableOptions = filterOptionsByUnselectableIds(
			selectableOptions,
			unselectableOptionIdsSet
		);
		validateOptions(createEstimateRequest, filteredSelectableOptions);

		UUID estimateUuid = estimatePort.createEstimate(createEstimateRequest);
		return CreateEstimateResponse.from(estimateUuid);
	}

	private void validateModel(Long modelId, List<Model> models) {
		models.stream()
			.filter(model -> model.isRightModel(modelId))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateTrim(CreateEstimateRequest createEstimateRequest, List<Trim> trims) {
		trims.stream()
			.filter(trim -> trim.isRightTrim(createEstimateRequest.getTrimId(), createEstimateRequest.getTrimPrice()))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateEngine(CreateEstimateRequest createEstimateRequest, List<Engine> engines) {
		engines.stream()
			.filter(engine -> engine.isRightEngine(
				createEstimateRequest.getEngineId(),
				createEstimateRequest.getEnginePrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateBodyType(CreateEstimateRequest createEstimateRequest, List<BodyType> bodyTypes) {
		bodyTypes.stream()
			.filter(bodyType -> bodyType.isRightBodyType(
				createEstimateRequest.getBodyTypeId(),
				createEstimateRequest.getBodyTypePrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateWheelDrive(
		CreateEstimateRequest createEstimateRequest,
		List<WheelDrive> wheelDrives
	) {
		wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isRightWheelDrive(
				createEstimateRequest.getWheelDriveId(),
				createEstimateRequest.getWheelDrivePrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateExteriorColor(
		CreateEstimateRequest createEstimateRequest,
		List<ExteriorColor> exteriorColors
	) {
		exteriorColors.stream()
			.filter(exteriorColor -> exteriorColor.isRightExteriorColor(
				createEstimateRequest.getExteriorColorId(),
				createEstimateRequest.getExteriorColorPrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateInteriorColor(
		CreateEstimateRequest createEstimateRequest,
		List<InteriorColor> selectableInteriorColors
	) {
		selectableInteriorColors.stream()
			.filter(interiorColor -> interiorColor.isRightInteriorColor(
				createEstimateRequest.getInteriorColorId(),
				createEstimateRequest.getInteriorColorPrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateOptions(CreateEstimateRequest createEstimateRequest, List<Option> filteredSelectableOptions) {
		List<Long> optionIds = Stream.concat(
				createEstimateRequest.getSelectOptions().stream(),
				createEstimateRequest.getConsiderOptions().stream()
			)
			.map(EstimateOptionDto::getId)
			.collect(Collectors.toList());

		List<Long> selectableOptionIds = filteredSelectableOptions.stream()
			.map(Option::getId)
			.collect(Collectors.toList());

		if (!selectableOptionIds.containsAll(optionIds)) {
			throw InvalidEstimationException.EXCEPTION;
		}
	}

	private Set<Long> combineUnselectableOptionIds(List<Long>... unselectableOptionIdsLists) {
		Set<Long> combinedSet = new HashSet<>();
		for (List<Long> list : unselectableOptionIdsLists) {
			combinedSet.addAll(list);
		}
		return combinedSet;
	}

	private List<Option> filterOptionsByUnselectableIds(
		List<Option> selectableOptions,
		Set<Long> unselectableOptionIdsSet
	) {
		return selectableOptions.stream()
			.filter(option -> !unselectableOptionIdsSet.contains(option.getId()))
			.collect(Collectors.toList());
	}
}
