package softeer.be_my_car_master.application.car_master.usecase.get_car_masters_in_agency;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.agency.dto.response.GetCarMastersInAgencyResponse;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetCarMastersInAgencyUseCase {

	private final GetCarMastersInAgencyPort port;

	public GetCarMastersInAgencyResponse execute(Long agencyId) {
		List<CarMaster> carMasters = port.findCarMastersByAgency(agencyId);
		return GetCarMastersInAgencyResponse.from(carMasters);
	}
}
