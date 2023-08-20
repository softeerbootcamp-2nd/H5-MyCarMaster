package softeer.be_my_car_master.api.option.usecase.get_default_options;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.response.GetDefaultOptionsResponse;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetDefaultOptionsUseCase {

	private final GetDefaultOptionsPort port;

	public GetDefaultOptionsResponse execute(Long trimId, Long engineId, Long wheelDriveId, Long bodyTypeId) {
		List<Option> defaultOptions = port.findDefaultOptionsByTrim(trimId);
		List<Long> unselectableOptionIdsByEngine = port.findUnselectableOptionIdsByEngine(engineId);
		List<Long> unselectableOptionIdsByWheelDrive = port.findUnselectableOptionIdsByWheelDrive(wheelDriveId);
		List<Long> unselectableOptionIdsByBodyType = port.findUnselectableOptionIdsByBodyType(bodyTypeId);

		Set<Long> unselectableOptionIdsSet = combineUnselectableOptionIds(
			unselectableOptionIdsByEngine,
			unselectableOptionIdsByWheelDrive,
			unselectableOptionIdsByBodyType
		);

		List<Option> filteredDefaultOptions = filterOptionsByUnselectableIds(
			defaultOptions,
			unselectableOptionIdsSet
		);

		return GetDefaultOptionsResponse.from(filteredDefaultOptions);
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
