package softeer.be_my_car_master.infrastructure.jpa.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

public interface ModelJpaRepository extends JpaRepository<ModelEntity, Long> {

	List<ModelEntity> findAllByOrderByCreatedAtDesc();
}
