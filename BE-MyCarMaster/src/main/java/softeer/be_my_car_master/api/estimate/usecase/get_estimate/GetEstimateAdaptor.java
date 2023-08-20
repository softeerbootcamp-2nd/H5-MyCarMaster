package softeer.be_my_car_master.api.estimate.usecase.get_estimate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;
import softeer.be_my_car_master.infrastructure.jpa.estimate.repository.EstimateJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetEstimateAdaptor implements GetEstimatePort {

	private final EstimateJpaRepository estimateJpaRepository;

	@Override
	@Transactional(readOnly = true)
	public Optional<Estimate> findFullEstimateByUuid(UUID estimateUuid) {
		return estimateJpaRepository.findFullEstimateByUuid(estimateUuid)
			.map(EstimateEntity::toEstimate);
	}
}
