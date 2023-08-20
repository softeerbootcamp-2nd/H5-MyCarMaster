package softeer.be_my_car_master.api.car_master.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.api.consult.usecase.port.CarMasterPort;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetCarMastersInAgencyUseCase {

	private final CarMasterPort carMasterPort;

	public GetCarMastersInAgencyResponse execute(Long agencyId) {
		List<CarMaster> carMasters = carMasterPort.findCarMastersByAgency(agencyId);
		return GetCarMastersInAgencyResponse.from(carMasters);
	}
}
