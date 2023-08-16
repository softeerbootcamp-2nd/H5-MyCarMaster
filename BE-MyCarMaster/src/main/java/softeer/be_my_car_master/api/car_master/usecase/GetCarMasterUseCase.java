package softeer.be_my_car_master.api.car_master.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.api.consult.usecase.port.CarMasterPort;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetCarMasterUseCase {

	private final CarMasterPort carMasterPort;

	public GetCarMasterResponse execute(Double latitude, Double longitude, String filter) {
		List<CarMaster> carMasters = carMasterPort.findCarMasters(latitude, longitude, filter);
		return GetCarMasterResponse.from(carMasters);
	}
}
