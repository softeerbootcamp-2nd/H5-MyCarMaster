package softeer.be_my_car_master.api.wheeldrive.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetWheelDrivesResponse {

	private List<WheelDriveDto> wheelDrives;
}
