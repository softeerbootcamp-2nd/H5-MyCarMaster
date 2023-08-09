package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.UnselectableBodyTypeOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.UnselectableEngineOptionEntity;

public interface UnselectableEngineOptionJpaRepository extends JpaRepository<UnselectableEngineOptionEntity, Long> {

	@Query("SELECT ueo.option.id "
		+ "FROM UnselectableEngineOptionEntity ueo "
		+ "WHERE ueo.engine.id = :engineId")
	List<Long> findUnselectableOptionIdsByEngineId(Long engineId);
}
