package softeer.be_my_car_master.application.car_master.usecase.get_car_masters;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.agency.entity.AgencyEntity;
import softeer.be_my_car_master.infrastructure.jpa.agency.repository.AgencyJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.CarMasterJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetCarMastersAdaptor implements GetCarMastersPort {

	private final AgencyJpaRepository agencyJpaRepository;
	private final CarMasterJpaRepository carMasterJpaRepository;

	@Override
	public List<Agency> findAgenciesByLocation(Double latitude, Double longitude) {
		return agencyJpaRepository.findAllByLocation(latitude, longitude).stream()
			.map(AgencyEntity::toAgency)
			.collect(Collectors.toList());
	}

	@Override
	public List<CarMaster> findCarMastersByAgenciesOrderBySales(List<Long> agencyIds) {
		return carMasterJpaRepository.findAllByAgencyIdsOrderBySales(agencyIds);
	}
}
