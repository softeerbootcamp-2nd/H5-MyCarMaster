package softeer.be_my_car_master.infrastructure.jpa.car_master.adaptor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.consult.usecase.port.CarMasterPort;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.CarMasterEntity;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.CarMasterJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class CarMasterJpaAdaptor implements CarMasterPort {

	private final CarMasterJpaRepository carMasterJpaRepository;

	@Override
	public Optional<CarMaster> findById(Long estimateId) {
		return carMasterJpaRepository.findById(estimateId)
			.map(CarMasterEntity::toCarMaster);
	}

	@Override
	public List<CarMaster> findCarMastersByAgency(Long agencyId) {
		return carMasterJpaRepository.findAllByAgency(agencyId).stream()
			.map(CarMasterEntity::toCarMaster)
			.collect(Collectors.toList());
	}

}
