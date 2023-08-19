package softeer.be_my_car_master.api.wheeldrive.usecase.get_wheel_drives;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.wheeldrive.dto.response.GetWheelDrivesResponse;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetWheelDrivesUseCase {

	private final GetWheelDrivesPort getWheelDrivePort;

	public GetWheelDrivesResponse execute(Long trimId, Long engineId) {
		List<WheelDrive> wheelDrives = getWheelDrivePort.findWheelDrivesByTrim(trimId);
		List<Long> unselectableWheelDriveIds = getWheelDrivePort.findUnselectableWheelDriveIdsByEngine(engineId);

		List<WheelDrive> selectableWheelDrives = wheelDrives.stream()
			.filter(wheelDrive -> wheelDrive.isSelectable(unselectableWheelDriveIds))
			.collect(Collectors.toList());

		return GetWheelDrivesResponse.from(selectableWheelDrives);
	}
}
