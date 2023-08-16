package softeer.be_my_car_master.infrastructure.jpa.estimate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;

public interface EstimateJpaRepository extends JpaRepository<EstimateEntity, UUID> {

	@Query("SELECT e FROM EstimateEntity e WHERE e.uuid = :estimateUuid")
	Optional<EstimateEntity> findByUuid(UUID estimateUuid);
}
