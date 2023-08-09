package softeer.be_my_car_master.infrastructure.trim;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrimJpaRepository extends JpaRepository<TrimEntity, Long> {

	@Query(value = "SELECT t FROM TrimEntity t WHERE t.model.id = :modelId")
	List<TrimEntity> findAllByModelId(Long modelId);
}
