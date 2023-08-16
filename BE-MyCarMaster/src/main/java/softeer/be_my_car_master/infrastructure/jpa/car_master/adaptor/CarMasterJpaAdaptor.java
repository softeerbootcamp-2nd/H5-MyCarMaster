package softeer.be_my_car_master.infrastructure.jpa.car_master.adaptor;

import java.util.Optional;

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
}
