package softeer.be_my_car_master.application.car_master.usecase.get_car_masters_in_agency;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.CarMasterEntity;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.CarMasterJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetCarMastersInAgencyAdaptor implements GetCarMastersInAgencyPort {

	private final CarMasterJpaRepository carMasterJpaRepository;

	@Override
	public List<CarMaster> findCarMastersByAgency(Long agencyId) {
		return carMasterJpaRepository.findAllByAgency(agencyId).stream()
			.map(CarMasterEntity::toCarMaster)
			.collect(Collectors.toList());
	}
}
