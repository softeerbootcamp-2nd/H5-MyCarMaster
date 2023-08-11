package softeer.be_my_car_master.api.engine.usecase;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;
import softeer.be_my_car_master.global.exception.InvalidOptionIdException;

@UseCase
@RequiredArgsConstructor
public class GetUnselectableOptionsByEngineUseCase {

	private final OptionPort optionPort;

	public GetUnselectableOptionsByEngineResponse execute(Long engineId, Long trimId, List<Long> selectedOptionIds) {
		List<Option> selectableOptions = optionPort.findSelectableOptionsByTrimId(trimId);
		List<Long> unselectableOptionIdsByEngine = optionPort.findUnselectableOptionIdsByEngineId(engineId);

		List<Option> unselectableOptionsByEngine = filterUnselectableOptionsByEngine(
			selectedOptionIds,
			selectableOptions,
			unselectableOptionIdsByEngine
		);
		return GetUnselectableOptionsByEngineResponse.from(unselectableOptionsByEngine);
	}

	private List<Option> filterUnselectableOptionsByEngine(
		List<Long> selectedOptionIds,
		List<Option> selectableOptions,
		List<Long> unselectableOptionIdsByEngine
	) {
		return selectedOptionIds.stream()
			.filter(
				optionId -> isUnselectableOptionByEngine(optionId, selectableOptions, unselectableOptionIdsByEngine)
			)
			.map(optionId -> findOptionById(selectableOptions, optionId))
			.collect(Collectors.toList());
	}

	private static boolean isUnselectableOptionByEngine(
		Long optionId,
		List<Option> selectableOptions,
		List<Long> unselectableOptionIdsByEngine
	) {
		if (!isValidOptionId(optionId, selectableOptions)) {
			throw new InvalidOptionIdException();
		}
		return unselectableOptionIdsByEngine.contains(optionId);
	}

	private static boolean isValidOptionId(Long optionId, List<Option> selectableOptions) {
		return selectableOptions.stream().anyMatch(option -> option.getId().equals(optionId));
	}

	private static Option findOptionById(List<Option> selectableOptions, Long optionId) {
		return selectableOptions.stream()
			.filter(option -> option.getId().equals(optionId))
			.findFirst()
			.orElseThrow(InvalidOptionIdException::new);
	}
}
