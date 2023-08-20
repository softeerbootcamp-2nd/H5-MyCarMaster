package softeer.be_my_car_master.api.agency.usecase.get_agencies;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.agency.entity.AgencyEntity;
import softeer.be_my_car_master.infrastructure.jpa.agency.repository.AgencyJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetAgenciesAdaptor implements GetAgenciesPort {

	private final AgencyJpaRepository agencyJpaRepository;

	@Override
	public List<Agency> findAgenciesInGu(String gu) {
		return agencyJpaRepository.findAllByGu(gu).stream()
			.map(AgencyEntity::toAgencyInGu)
			.collect(Collectors.toList());
	}
}
