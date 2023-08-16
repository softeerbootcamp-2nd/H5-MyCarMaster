package softeer.be_my_car_master.api.option.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface OptionPort {

	List<Option> findSelectableOptionsByTrimId(Long trimId);

	List<Option> findUnselectableOptions(Long trimId, List<Long> optionIds);

	List<Long> findSelectableOptionIdsByTrimId(Long trimId);

	List<Long> findUnselectableOptionIdsByEngineId(Long engineId);

	List<Long> findUnselectableOptionIdsByWheelDriveId(Long wheelDriveId);

	List<Long> findUnselectableOptionIdsByBodyTypeId(Long bodyTypeId);

	List<Long> findUnselectableOptionIdsByInteriorColorId(Long interiorColorId);

	List<Option> findDefaultOptionsByTrimId(Long trimId);

	List<Option> findRepresentativeOptionsByModelId(Long modelId);

	List<Long> findAdditionalTrimIdsByOptionId(Long optionId);

	List<Long> findDefaultTrimIdsByOptionId(Long optionId);

	List<Option> findAppliedOptionsByModelIdAndOptionIds(Long modelId, List<Long> optionIds);
}
