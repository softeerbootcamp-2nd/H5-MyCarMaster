package softeer.be_my_car_master.infrastructure.jpa.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.engine.entity.TrimEngineEntity;

public interface TrimEngineJpaRepository extends JpaRepository<TrimEngineEntity, Long> {

	@Query(value = "SELECT te "
		+ "FROM TrimEngineEntity te "
		+ "JOIN FETCH te.engine "
		+ "WHERE te.trim.id = :trimId")
	List<TrimEngineEntity> findAllByTrimId(Long trimId);
}
