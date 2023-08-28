package softeer.be_my_car_master.application.option.usecase.get_options;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.option.dto.response.GetOptionsResponse;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetOptionsUseCase {

	private final GetOptionsPort port;

	@Cacheable(value = "get_options",
		key =
			"'trimId=' + #trimId "
				+ "+ '_engineId=' + #engineId "
				+ "+ '_wheelDriveId=' + #wheelDriveId "
				+ "+ '_bodyTypeId=' + #bodyTypeId "
				+ "+ '_interiorColorId=' + #interiorColorId",
		unless = "#result == null")
	public GetOptionsResponse execute(
		Long trimId,
		Long engineId,
		Long wheelDriveId,
		Long bodyTypeId,
		Long interiorColorId
	) {
		List<Option> options = port.findOptionsByTrim(trimId);
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

		List<Option> filteredSelectableOptions = filterOptionsByUnselectableIds(
			options,
			unselectableOptionIdsSet
		);

		List<String> singleSelectableTags = port.findSingleSelectableTags();

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
