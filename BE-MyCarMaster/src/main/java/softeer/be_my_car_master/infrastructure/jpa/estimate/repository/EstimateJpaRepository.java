package softeer.be_my_car_master.infrastructure.jpa.estimate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;

public interface EstimateJpaRepository extends JpaRepository<EstimateEntity, UUID> {

	@Query("SELECT e FROM EstimateEntity e WHERE e.uuid = :estimateUuid")
	Optional<EstimateEntity> findByUuid(UUID estimateUuid);

	@Query("SELECT e "
		+ "FROM EstimateEntity e "
		+ "JOIN FETCH e.trim "
		+ "JOIN FETCH e.engine "
		+ "JOIN FETCH e.wheelDrive "
		+ "JOIN FETCH e.bodyType "
		+ "JOIN FETCH e.exteriorColor "
		+ "JOIN FETCH e.interiorColor "
		+ "WHERE e.uuid = :estimateUuid")
	Optional<EstimateEntity> findFullEstimateByUuid(UUID estimateUuid);
}
