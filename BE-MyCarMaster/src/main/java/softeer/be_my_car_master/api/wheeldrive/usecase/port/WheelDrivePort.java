package softeer.be_my_car_master.api.wheeldrive.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface WheelDrivePort {

	List<WheelDrive> findSelectableWheelDrivesByTrimId(Long trimId);

	List<Long> findUnselectableWheelDriveIdsByEngineId(Long engineId);
}
