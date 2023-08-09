package softeer.be_my_car_master.api.option.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.api.option.usecase.port.TagPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetOptionsUseCase {

	private final OptionPort optionPort;
	private final TagPort tagPort;

	public GetOptionsResponse execute(
		Long trimId,
		Long engineId,
		Long wheelDriveId,
		Long bodyTypeId,
		Long interiorColorId
	) {
		List<Option> selectableOptions = optionPort.findSelectableOptionsByTrimId(trimId);
		List<Long> unselectableOptionIdsByEngine = optionPort.findUnselectableOptionIdsByEngineId(engineId);
		List<Long> unselectableOptionIdsByWheelDrive = optionPort.findUnselectableOptionIdsByWheelDriveId(wheelDriveId);
		List<Long> unselectableOptionIdsByBodyType = optionPort.findUnselectableOptionIdsByBodyTypeId(bodyTypeId);
		List<Long> unselectableOptionIdsByInteriorColor =
			optionPort.findUnselectableOptionIdsByInteriorColorId(interiorColorId);

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

		List<String> singleSelectableTags = tagPort.findSingleSelectableTags();

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
		List<Option> selectableOptions,
		Set<Long> unselectableOptionIdsSet
	) {
		return selectableOptions.stream()
			.filter(option -> unselectableOptionIdsSet.contains(option.getId()))
			.collect(Collectors.toList());
	}
}
