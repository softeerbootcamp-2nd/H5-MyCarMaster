package softeer.be_my_car_master.api.engine.usecase;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetUnselectableOptionsByEngineUseCase {

	private final OptionPort optionPort;

	public GetUnselectableOptionsByEngineResponse execute(Long engineId, Long trimId, List<Long> selectedOptionIds) {
		List<Option> selectableOptions = optionPort.findSelectableOptionsByTrimId(trimId);
		List<Long> unselectableOptionIdsByEngine = optionPort.findUnselectableOptionIdsByEngineId(engineId);

		List<Option> unselectableOptionsByEngine = selectableOptions.stream()
			.filter(option -> isUnselectableOptionByEngine(option, selectedOptionIds, unselectableOptionIdsByEngine))
			.collect(Collectors.toList());

		return GetUnselectableOptionsByEngineResponse.from(unselectableOptionsByEngine);
	}

	private static boolean isUnselectableOptionByEngine(Option option, List<Long> selectedOptionIds,
		List<Long> unselectableOptionIdsByEngine) {
		return selectedOptionIds.contains(option.getId()) && unselectableOptionIdsByEngine.contains(option.getId());
	}
}
