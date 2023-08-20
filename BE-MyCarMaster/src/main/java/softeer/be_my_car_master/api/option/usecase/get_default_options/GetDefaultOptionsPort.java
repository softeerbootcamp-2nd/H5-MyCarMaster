package softeer.be_my_car_master.api.option.usecase.get_default_options;

import java.util.List;

import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetDefaultOptionsPort {

	List<Option> findDefaultOptionsByTrim(Long trimId);

	List<Long> findUnselectableOptionIdsByEngine(Long engineId);

	List<Long> findUnselectableOptionIdsByWheelDrive(Long wheelDriveId);

	List<Long> findUnselectableOptionIdsByBodyType(Long bodyTypeId);
}
