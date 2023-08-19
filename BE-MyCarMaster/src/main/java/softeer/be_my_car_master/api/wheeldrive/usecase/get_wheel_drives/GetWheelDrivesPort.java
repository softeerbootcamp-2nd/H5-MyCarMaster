package softeer.be_my_car_master.api.wheeldrive.usecase.get_wheel_drives;

import java.util.List;

import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetWheelDrivesPort {

	List<WheelDrive> findSelectableWheelDrivesByTrimId(Long trimId);

	List<Long> findUnselectableWheelDriveIdsByEngineId(Long engineId);
}
