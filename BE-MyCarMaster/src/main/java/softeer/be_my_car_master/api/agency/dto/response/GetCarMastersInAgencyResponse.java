package softeer.be_my_car_master.api.agency.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.car_master.CarMaster;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMastersInAgencyResponse {

	private List<CarMasterInAgencyDto> carMasters;

	public static GetCarMastersInAgencyResponse from(List<CarMaster> carMasters) {
		List<CarMasterInAgencyDto> carMasterInAgencyDtos = carMasters.stream()
			.map(CarMasterInAgencyDto::from)
			.collect(Collectors.toList());
		return new GetCarMastersInAgencyResponse(carMasterInAgencyDtos);
	}
}
