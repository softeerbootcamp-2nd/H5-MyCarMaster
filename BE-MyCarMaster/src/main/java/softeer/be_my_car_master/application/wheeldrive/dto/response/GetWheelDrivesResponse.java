package softeer.be_my_car_master.application.wheeldrive.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetWheelDrivesResponse {

	private List<WheelDriveDto> wheelDrives;

	public static GetWheelDrivesResponse from(List<WheelDrive> selectableWheelDrives) {
		List<WheelDriveDto> wheelDriveDtos = selectableWheelDrives.stream()
			.map(WheelDriveDto::from)
			.collect(Collectors.toList());
		return new GetWheelDrivesResponse(wheelDriveDtos);
	}
}
