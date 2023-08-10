package softeer.be_my_car_master.infrastructure.jpa.body_type.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.body_type.entity.BodyTypeEntity;

public interface BodyTypeJpaRepository extends JpaRepository<BodyTypeEntity, Long> {

	@Query(value = "SELECT b FROM BodyTypeEntity b WHERE b.model.id = :modelId")
	List<BodyTypeEntity> findAllByModelId(Long modelId);
}
