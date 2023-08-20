package softeer.be_my_car_master.api.option.usecase.get_representative_options;

import java.util.List;

import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetRepresentativeOptionsPort {

	List<Option> findRepresentativeOptionsByModel(Long modelId);

	List<Option> findAppliedOptionsByModelAndOptions(Long modelId, List<Long> optionIds);

	List<Long> findTrimIdsByModel(Long modelId);

	List<Long> findAdditionalTrimIdsByOption(Long optionId);

	List<Long> findDefaultTrimIdsByOption(Long optionId);
}
