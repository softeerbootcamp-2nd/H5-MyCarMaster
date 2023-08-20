package softeer.be_my_car_master.application.wheeldrive.usecase.get_wheel_drives;

import java.util.List;

import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetWheelDrivesPort {

	List<WheelDrive> findWheelDrivesByTrim(Long trimId);

	List<Long> findUnselectableWheelDriveIdsByEngine(Long engineId);
}
