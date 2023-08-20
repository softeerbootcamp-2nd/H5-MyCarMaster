package softeer.be_my_car_master.application.option.usecase.get_options;

import java.util.List;

import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetOptionsPort {

	List<Option> findOptionsByTrim(Long trimId);

	List<Long> findUnselectableOptionIdsByEngine(Long engineId);

	List<Long> findUnselectableOptionIdsByWheelDrive(Long wheelDriveId);

	List<Long> findUnselectableOptionIdsByBodyType(Long bodyTypeId);

	List<Long> findUnselectableOptionIdsByInteriorColor(Long interiorColorId);

	List<String> findSingleSelectableTags();
}
