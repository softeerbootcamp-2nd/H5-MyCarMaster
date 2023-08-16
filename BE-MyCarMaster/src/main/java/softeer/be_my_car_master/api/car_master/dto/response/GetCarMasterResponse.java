package softeer.be_my_car_master.api.car_master.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarMasterResponse {

	private List<CarMasterDto> carMasters;
}
