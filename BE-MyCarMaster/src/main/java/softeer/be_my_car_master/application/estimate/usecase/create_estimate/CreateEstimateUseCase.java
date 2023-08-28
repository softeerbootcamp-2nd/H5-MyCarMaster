package softeer.be_my_car_master.application.estimate.usecase.create_estimate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.application.estimate.dto.request.EstimateOptionRequestDto;
import softeer.be_my_car_master.application.estimate.dto.response.CreateEstimateResponse;
import softeer.be_my_car_master.application.estimate.exception.InvalidEstimationException;
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

	private final CreateEstimatePort port;

	@Transactional
	public CreateEstimateResponse execute(CreateEstimateRequest request) {
		validateEstimate(request);

		Long modelId = request.getModelId();
		Long trimId = request.getTrimId();
		Long engineId = request.getEngineId();
		Long wheelDriveId = request.getWheelDriveId();
		Long bodyTypeId = request.getBodyTypeId();
		Long exteriorColorId = request.getExteriorColorId();
		Long interiorColorId = request.getInteriorColorId();
		List<Long> selectOptionIds = request.getSelectOptions().stream()
			.map(EstimateOptionRequestDto::getId)
			.collect(Collectors.toList());
		List<Long> considerOptionIds = request.getConsiderOptions().stream()
			.map(EstimateOptionRequestDto::getId)
			.collect(Collectors.toList());

		UUID estimateUuid = port.createEstimate(
			modelId, trimId, engineId, wheelDriveId, bodyTypeId,
			exteriorColorId, interiorColorId, selectOptionIds, considerOptionIds
		);
		return CreateEstimateResponse.from(estimateUuid);
	}

	private void validateEstimate(CreateEstimateRequest request) {
		Long modelId = request.getModelId();
		List<Model> models = port.findModels();
		validateModel(modelId, models);

		Long trimId = request.getTrimId();
		Integer trimPrice = request.getTrimPrice();
		List<Trim> trims = port.findTrimsByModel(modelId);
		validateTrim(trimId, trimPrice, trims);

		Long engineId = request.getEngineId();
		Integer enginePrice = request.getEnginePrice();
		List<Engine> engines = port.findEnginesByTrim(trimId);
		validateEngine(engineId, enginePrice, engines);

		Long bodyTypeId = request.getBodyTypeId();
		Integer bodyTypePrice = request.getBodyTypePrice();
		List<BodyType> bodyTypes = port.findBodyTypesByModel(modelId);
		validateBodyType(bodyTypeId, bodyTypePrice, bodyTypes);

		Long wheelDriveId = request.getWheelDriveId();
		Integer wheelDrivePrice = request.getWheelDrivePrice();
		List<WheelDrive> wheelDrives = port.findWheelDrivesByTrim(trimId);
		List<Long> unselectableWheelDriveIds = port.findUnselectableWheelDriveIdsByEngine(engineId);
		List<WheelDrive> selectableWheelDrives = wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isSelectable(unselectableWheelDriveIds))
			.collect(Collectors.toList());
		validateWheelDrive(wheelDriveId, wheelDrivePrice, selectableWheelDrives);

		Long exteriorColorId = request.getExteriorColorId();
		Integer exteriorColorPrice = request.getExteriorColorPrice();
		List<ExteriorColor> exteriorColors = port.findExteriorColorsByTrim(trimId);
		validateExteriorColor(exteriorColorId, exteriorColorPrice, exteriorColors);

		Long interiorColorId = request.getInteriorColorId();
		Integer interiorColorPrice = request.getInteriorColorPrice();
		List<InteriorColor> interiorColors = port.findInteriorColorsByTrim(trimId);
		List<Long> unselectableInteriorColorIds = port.findUnselectableInteriorColorIdsByExteriorColor(exteriorColorId);
		List<InteriorColor> selectableInteriorColors = interiorColors.stream()
			.filter(interiorColor -> interiorColor.isSelectable(unselectableInteriorColorIds))
			.collect(Collectors.toList());
		validateInteriorColor(interiorColorId, interiorColorPrice, selectableInteriorColors);

		List<Option> selectableOptions = port.findOptionsByTrim(trimId);
		List<Long> unselectableOptionIdsByEngine = port.findUnselectableOptionIdsByEngine(engineId);
		List<Long> unselectableOptionIdsByWheelDrive = port.findUnselectableOptionIdsByWheelDrive(wheelDriveId);
		List<Long> unselectableOptionIdsByBodyType = port.findUnselectableOptionIdsByBodyType(bodyTypeId);
		List<Long> unselectableOptionIdsByInteriorColor
			= port.findUnselectableOptionIdsByInteriorColor(interiorColorId);

		Set<Long> unselectableOptionIdsSet = combineUnselectableOptionIds(
			unselectableOptionIdsByEngine,
			unselectableOptionIdsByWheelDrive,
			unselectableOptionIdsByBodyType,
			unselectableOptionIdsByInteriorColor
		);

		List<EstimateOptionRequestDto> selectOptions = request.getSelectOptions();
		List<EstimateOptionRequestDto> considerOptions = request.getConsiderOptions();
		List<Option> filteredSelectableOptions = filterOptionsByUnselectableIds(
			selectableOptions,
			unselectableOptionIdsSet
		);
		validateOptions(selectOptions, considerOptions, filteredSelectableOptions);
	}

	private void validateModel(Long modelId, List<Model> models) {
		models.stream()
			.filter(model -> model.isRightModel(modelId))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateTrim(Long trimId, Integer trimPrice, List<Trim> trims) {
		trims.stream()
			.filter(trim -> trim.isRightTrim(trimId, trimPrice))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateEngine(Long engineId, Integer enginePrice, List<Engine> engines) {
		engines.stream()
			.filter(engine -> engine.isRightEngine(engineId, enginePrice))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateBodyType(Long bodyTypeId, Integer bodyTypePrice, List<BodyType> bodyTypes) {
		bodyTypes.stream()
			.filter(bodyType -> bodyType.isRightBodyType(bodyTypeId, bodyTypePrice))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateWheelDrive(Long wheelDriveId, Integer wheelDrivePrice, List<WheelDrive> wheelDrives) {
		wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isRightWheelDrive(wheelDriveId, wheelDrivePrice))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateExteriorColor(
		Long exteriorColorId,
		Integer exteriorColorPrice,
		List<ExteriorColor> exteriorColors
	) {
		exteriorColors.stream()
			.filter(exteriorColor -> exteriorColor.isRightExteriorColor(exteriorColorId, exteriorColorPrice))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateInteriorColor(
		Long interiorColorId,
		Integer interiorColorPrice,
		List<InteriorColor> selectableInteriorColors
	) {
		selectableInteriorColors.stream()
			.filter(interiorColor -> interiorColor.isRightInteriorColor(interiorColorId, interiorColorPrice))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateOptions(
		List<EstimateOptionRequestDto> selectOptions,
		List<EstimateOptionRequestDto> considerOptions,
		List<Option> filteredSelectableOptions
	) {
		List<Long> optionIds = Stream.concat(selectOptions.stream(), considerOptions.stream())
			.map(EstimateOptionRequestDto::getId)
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
