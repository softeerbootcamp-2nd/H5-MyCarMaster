package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.EngineUnselectableOptionEntity;

public interface EngineUnselectableOptionJpaRepository extends JpaRepository<EngineUnselectableOptionEntity, Long> {

	@Query("SELECT euo.option.id "
		+ "FROM EngineUnselectableOptionEntity euo "
		+ "WHERE euo.engine.id = :engineId")
	List<Long> findUnselectableOptionIdsByEngineId(Long engineId);
}
