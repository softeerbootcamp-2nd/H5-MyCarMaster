package softeer.be_my_car_master.application.consult.usecase.apply_consulting;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.CarMasterEntity;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.ConsultingEntity;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.CarMasterJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.car_master.repository.ConsultingJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;
import softeer.be_my_car_master.infrastructure.jpa.estimate.repository.EstimateJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class ApplyConsultingAdaptor implements ApplyConsultingPort {

	private final EstimateJpaRepository estimateJpaRepository;
	private final CarMasterJpaRepository carMasterJpaRepository;
	private final ConsultingJpaRepository consultingJpaRepository;

	@Override
	public Optional<Estimate> findEstimateByUuid(UUID estimateUuid) {
		return estimateJpaRepository.findByUuid(estimateUuid)
			.map(EstimateEntity::toSimpleEstimate);
	}

	@Override
	public Optional<CarMaster> findCarMasterById(Long carMasterId) {
		return carMasterJpaRepository.findById(carMasterId)
			.map(CarMasterEntity::toCarMaster);
	}

	@Override
	public void createConsulting(Consulting consulting) {
		consultingJpaRepository.save(ConsultingEntity.from(consulting));
	}
}
