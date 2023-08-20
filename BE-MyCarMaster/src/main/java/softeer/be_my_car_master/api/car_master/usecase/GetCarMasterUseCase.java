package softeer.be_my_car_master.api.car_master.usecase;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.api.car_master.usecase.port.AgencyPort;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetCarMasterUseCase {

	private final AgencyPort agencyPort;

	public GetCarMasterResponse execute(Double latitude, Double longitude) {
		List<Agency> agencies = agencyPort.findAgenciesAndCarMasters(latitude, longitude);

		Stream<CarMaster> allCarMaster = getFlatMapCarMaster(agencies);
		List<CarMaster> sortedCarMaster = getSortedCarMaster(allCarMaster);

		return GetCarMasterResponse.from(agencies, sortedCarMaster);
	}

	private static List<CarMaster> getSortedCarMaster(Stream<CarMaster> allCarMaster) {
		return allCarMaster
			.sorted(Comparator.comparing(CarMaster::getSales).reversed())
			.collect(Collectors.toList());
	}

	private static Stream<CarMaster> getFlatMapCarMaster(List<Agency> agencies) {
		Stream<CarMaster> allCarMaster = agencies.stream()
			.flatMap(agency -> agency.getCarMasters().stream());
		return allCarMaster;
	}
}