package softeer.be_my_car_master.application.option.usecase.get_unselectable_options_by_engine;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.application.engine.exception.InvalidOptionException;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetUnselectableOptionsByEngineUseCase {

	private final GetUnselectableOptionsByEnginePort port;

	public GetUnselectableOptionsByEngineResponse execute(
		Long selectedEngineId,
		Long trimId,
		List<Long> selectedOptionIds
	) {
		List<Long> optionIdsByTrim = port.findOptionIdsByTrim(trimId);
		validateTrimOptions(selectedOptionIds, optionIdsByTrim);

		List<Long> unselectableOptionIdsByEngine = port.findUnselectableOptionIdsByEngine(selectedEngineId);
		List<Long> unselectableOptionIds
			= getUnselectableOptionIdsByEngine(selectedOptionIds, unselectableOptionIdsByEngine);

		List<Option> unselectableOptions = port.findUnselectableOptions(trimId, unselectableOptionIds);

		return GetUnselectableOptionsByEngineResponse.from(unselectableOptions);
	}

	private void validateTrimOptions(
		List<Long> selectedOptions,
		List<Long> selectableOptionIdsInTrim
	) {
		if (!selectableOptionIdsInTrim.containsAll(selectedOptions)) {
			throw InvalidOptionException.EXCEPTION;
		}
	}

	private List<Long> getUnselectableOptionIdsByEngine(
		List<Long> selectedOptionIds,
		List<Long> unselectableOptionIdsByEngine
	) {
		return selectedOptionIds.stream()
			.filter(selectedOptionId -> unselectableOptionIdsByEngine.contains(selectedOptionId))
			.collect(Collectors.toList());
	}
}
