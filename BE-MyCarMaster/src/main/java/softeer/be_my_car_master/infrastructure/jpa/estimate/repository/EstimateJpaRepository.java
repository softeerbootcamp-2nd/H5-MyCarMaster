package softeer.be_my_car_master.infrastructure.jpa.estimate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.estimate.entity.EstimateEntity;

public interface EstimateJpaRepository extends JpaRepository<EstimateEntity, Long> {
}
