package softeer.be_my_car_master.api.car_master.usecase.get_car_masters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.agency.entity.AgencyEntity;
import softeer.be_my_car_master.infrastructure.jpa.agency.repository.AgencyJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetCarMastersAdaptor implements GetCarMastersPort {

	private final AgencyJpaRepository agencyJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Agency> findAgenciesAndCarMasters(Double latitude, Double longitude) {
		return agencyJpaRepository.findAllByLocation(latitude, longitude).stream()
			.map(AgencyEntity::toAgency)
			.collect(Collectors.toList());
	}
}
