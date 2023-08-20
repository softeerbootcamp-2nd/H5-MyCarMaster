package softeer.be_my_car_master.api.option.usecase.get_unselectable_options_by_engine;

import java.util.List;

import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetUnselectableOptionsByEnginePort {

	List<Long> findOptionIdsByTrim(Long trimId);

	List<Long> findUnselectableOptionIdsByEngine(Long engineId);

	List<Option> findUnselectableOptions(Long trimId, List<Long> optionIds);
}
