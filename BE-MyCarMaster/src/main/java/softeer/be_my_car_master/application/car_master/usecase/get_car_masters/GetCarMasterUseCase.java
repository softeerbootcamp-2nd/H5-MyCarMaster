package softeer.be_my_car_master.application.car_master.usecase.get_car_masters;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.car_master.dto.response.GetCarMasterResponse;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetCarMasterUseCase {

	private final GetCarMastersPort port;

	// @Cacheable(value = "redis", key = "#latitude + '_' + #longitude", unless = "#result == null")
	public GetCarMasterResponse execute(Double latitude, Double longitude) {
		List<Agency> agencies = port.findAgenciesByLocation(latitude, longitude);

		List<Long> agencyIds = agencies.stream()
			.map(Agency::getId)
			.collect(Collectors.toList());
		List<CarMaster> carMasters = port.findCarMastersByAgenciesOrderBySales(agencyIds);

		return GetCarMasterResponse.from(agencies, carMasters);
	}
}
