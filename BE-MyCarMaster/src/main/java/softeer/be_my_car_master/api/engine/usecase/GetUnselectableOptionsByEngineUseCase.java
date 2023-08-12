package softeer.be_my_car_master.api.engine.usecase;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;
import softeer.be_my_car_master.global.exception.InvalidOptionException;

@UseCase
@RequiredArgsConstructor
public class GetUnselectableOptionsByEngineUseCase {

	private final OptionPort optionPort;

	public GetUnselectableOptionsByEngineResponse execute(Long selectedEngineId, Long trimId,
		List<Long> selectedOptionIds) {
		List<Long> selectableOptionIdsInTrim = optionPort.findSelectableOptionIdsByTrimId(trimId);
		validateTrimOptions(selectedOptionIds, selectableOptionIdsInTrim);

		List<Long> unselectableOptionIdsByEngine = optionPort.findUnselectableOptionIdsByEngineId(selectedEngineId);
		List<Long> unselectableOptionIds =
			getUnselectableOptionIdsByEngine(selectedOptionIds, unselectableOptionIdsByEngine);

		List<Option> unselectableOptions = optionPort.findUnselectableOptions(trimId, unselectableOptionIds);

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
