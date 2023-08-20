package softeer.be_my_car_master.api.option.usecase.get_options;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetOptionsUseCase {

	private final GetOptionsPort getOptionsPort;

	public GetOptionsResponse execute(
		Long trimId,
		Long engineId,
		Long wheelDriveId,
		Long bodyTypeId,
		Long interiorColorId
	) {
		List<Option> options = getOptionsPort.findOptionsByTrim(trimId);
		List<Long> unselectableOptionIdsByEngine = getOptionsPort.findUnselectableOptionIdsByEngine(engineId);
		List<Long> unselectableOptionIdsByWheelDrive = getOptionsPort.findUnselectableOptionIdsByWheelDrive(wheelDriveId);
		List<Long> unselectableOptionIdsByBodyType = getOptionsPort.findUnselectableOptionIdsByBodyType(bodyTypeId);
		List<Long> unselectableOptionIdsByInteriorColor =
			getOptionsPort.findUnselectableOptionIdsByInteriorColor(interiorColorId);

		Set<Long> unselectableOptionIdsSet = combineUnselectableOptionIds(
			unselectableOptionIdsByEngine,
			unselectableOptionIdsByWheelDrive,
			unselectableOptionIdsByBodyType,
			unselectableOptionIdsByInteriorColor
		);

		List<Option> filteredSelectableOptions = filterOptionsByUnselectableIds(
			options,
			unselectableOptionIdsSet
		);

		List<String> singleSelectableTags = getOptionsPort.findSingleSelectableTags();

		return GetOptionsResponse.from(filteredSelectableOptions, singleSelectableTags);
	}

	private Set<Long> combineUnselectableOptionIds(List<Long>... unselectableOptionIdsLists) {
		Set<Long> combinedSet = new HashSet<>();
		for (List<Long> list : unselectableOptionIdsLists) {
			combinedSet.addAll(list);
		}
		return combinedSet;
	}

	private List<Option> filterOptionsByUnselectableIds(
		List<Option> options,
		Set<Long> unselectableOptionIdsSet
	) {
		return options.stream()
			.filter(option -> !unselectableOptionIdsSet.contains(option.getId()))
			.collect(Collectors.toList());
	}
}
