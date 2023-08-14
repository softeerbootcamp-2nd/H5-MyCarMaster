package softeer.be_my_car_master.api.estimate.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.body_type.usecase.port.BodyTypePort;
import softeer.be_my_car_master.api.color_exterior.usecase.port.ExteriorColorPort;
import softeer.be_my_car_master.api.color_interior.usecase.port.InteriorColorPort;
import softeer.be_my_car_master.api.engine.usecase.port.EnginePort;
import softeer.be_my_car_master.api.estimate.dto.request.EstimateOptionDto;
import softeer.be_my_car_master.api.estimate.dto.request.MakeUpEstimateRequest;
import softeer.be_my_car_master.api.estimate.dto.response.MakeUpEstimateResponse;
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
public class MakeUpEstimateUseCase {

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
	public MakeUpEstimateResponse execute(MakeUpEstimateRequest makeUpEstimateRequest) {
		Long modelId = makeUpEstimateRequest.getModelId();
		List<Model> models = modelPort.findModels();
		validateModel(modelId, models);

		List<Trim> trims = trimPort.findTrims(modelId);
		validateTrim(makeUpEstimateRequest, trims);

		List<Engine> engines = enginePort.findSelectableEnginesByTrimId(makeUpEstimateRequest.getTrimId());
		validateEngine(makeUpEstimateRequest, engines);

		List<BodyType> bodyTypes = bodyTypePort.findSelectableBodyTypesByModelId(modelId);
		validateBodyType(makeUpEstimateRequest, bodyTypes);

		List<WheelDrive> wheelDrives =
			wheelDrivePort.findSelectableWheelDrivesByTrimId(makeUpEstimateRequest.getTrimId());
		List<Long> unselectableWheelDriveIds =
			wheelDrivePort.findUnselectableWheelDriveIdsByEngineId(makeUpEstimateRequest.getEngineId());
		List<WheelDrive> selectableWheelDrives = wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isSelectable(unselectableWheelDriveIds))
			.collect(Collectors.toList());
		validateWheelDrive(makeUpEstimateRequest, selectableWheelDrives);

		List<ExteriorColor> exteriorColors =
			exteriorColorPort.findSelectableExteriorColorsByTrimId(makeUpEstimateRequest.getTrimId());
		validateExteriorColor(makeUpEstimateRequest, exteriorColors);

		List<InteriorColor> interiorColors =
			interiorColorPort.findSelectableInteriorColorsByTrimId(makeUpEstimateRequest.getTrimId());
		List<Long> unselectableInteriorColorIds =
			interiorColorPort.findUnselectableInteriorColorIdsByExteriorColorId(
				makeUpEstimateRequest.getExteriorColorId());
		List<InteriorColor> selectableInteriorColors = interiorColors.stream()
			.filter(interiorColor -> interiorColor.isSelectable(unselectableInteriorColorIds))
			.collect(Collectors.toList());
		validateInteriorColor(makeUpEstimateRequest, selectableInteriorColors);

		List<Option> selectableOptions = optionPort.findSelectableOptionsByTrimId(makeUpEstimateRequest.getTrimId());
		List<Long> unselectableOptionIdsByEngine
			= optionPort.findUnselectableOptionIdsByEngineId(makeUpEstimateRequest.getEngineId());
		List<Long> unselectableOptionIdsByWheelDrive
			= optionPort.findUnselectableOptionIdsByWheelDriveId(makeUpEstimateRequest.getWheelDriveId());
		List<Long> unselectableOptionIdsByBodyType
			= optionPort.findUnselectableOptionIdsByBodyTypeId(makeUpEstimateRequest.getBodyTypeId());
		List<Long> unselectableOptionIdsByInteriorColor =
			optionPort.findUnselectableOptionIdsByInteriorColorId(makeUpEstimateRequest.getInteriorColorId());

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
		validateOptions(makeUpEstimateRequest, filteredSelectableOptions);

		Long estimateId = estimatePort.makeUpEstimate(makeUpEstimateRequest);
		return MakeUpEstimateResponse.from(estimateId);
	}

	private void validateModel(Long modelId, List<Model> models) {
		models.stream()
			.filter(model -> model.isRightModel(modelId))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateTrim(MakeUpEstimateRequest makeUpEstimateRequest, List<Trim> trims) {
		trims.stream()
			.filter(trim -> trim.isRightTrim(makeUpEstimateRequest.getTrimId(), makeUpEstimateRequest.getTrimPrice()))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateEngine(MakeUpEstimateRequest makeUpEstimateRequest, List<Engine> engines) {
		engines.stream()
			.filter(engine -> engine.isRightEngine(
				makeUpEstimateRequest.getEngineId(),
				makeUpEstimateRequest.getEnginePrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateBodyType(MakeUpEstimateRequest makeUpEstimateRequest, List<BodyType> bodyTypes) {
		bodyTypes.stream()
			.filter(bodyType -> bodyType.isRightBodyType(
				makeUpEstimateRequest.getBodyTypeId(),
				makeUpEstimateRequest.getBodyTypePrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateWheelDrive(
		MakeUpEstimateRequest makeUpEstimateRequest,
		List<WheelDrive> wheelDrives
	) {
		wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isRightWheelDrive(
				makeUpEstimateRequest.getWheelDriveId(),
				makeUpEstimateRequest.getWheelDrivePrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateExteriorColor(
		MakeUpEstimateRequest makeUpEstimateRequest,
		List<ExteriorColor> exteriorColors
	) {
		exteriorColors.stream()
			.filter(exteriorColor -> exteriorColor.isRightExteriorColor(
				makeUpEstimateRequest.getExteriorColorId(),
				makeUpEstimateRequest.getExteriorColorPrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateInteriorColor(
		MakeUpEstimateRequest makeUpEstimateRequest,
		List<InteriorColor> selectableInteriorColors
	) {
		selectableInteriorColors.stream()
			.filter(interiorColor -> interiorColor.isRightInteriorColor(
				makeUpEstimateRequest.getInteriorColorId(),
				makeUpEstimateRequest.getInteriorColorPrice()
			))
			.findAny()
			.orElseThrow(() -> InvalidEstimationException.EXCEPTION);
	}

	private void validateOptions(MakeUpEstimateRequest makeUpEstimateRequest, List<Option> filteredSelectableOptions) {
		List<Long> optionIds = Stream.concat(
				makeUpEstimateRequest.getSelectOptions().stream(),
				makeUpEstimateRequest.getConsiderOptions().stream()
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
