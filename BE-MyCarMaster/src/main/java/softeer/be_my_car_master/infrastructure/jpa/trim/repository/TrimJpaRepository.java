package softeer.be_my_car_master.infrastructure.jpa.trim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;

public interface TrimJpaRepository extends JpaRepository<TrimEntity, Long> {

	@Query(value = "SELECT t FROM TrimEntity t WHERE t.model.id = :modelId")
	List<TrimEntity> findTrimsByModelId(Long modelId);
}
