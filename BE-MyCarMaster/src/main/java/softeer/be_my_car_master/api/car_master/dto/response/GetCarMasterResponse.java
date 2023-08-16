package softeer.be_my_car_master.api.car_master.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.car_master.CarMaster;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarMasterResponse {

	private List<CarMasterDto> carMasters;

	public static GetCarMasterResponse from(List<CarMaster> carMasters) {
		List<CarMasterDto> carMasterDtos = carMasters.stream()
			.map(CarMasterDto::from)
			.collect(Collectors.toList());
		return new GetCarMasterResponse(carMasterDtos);
	}
}
