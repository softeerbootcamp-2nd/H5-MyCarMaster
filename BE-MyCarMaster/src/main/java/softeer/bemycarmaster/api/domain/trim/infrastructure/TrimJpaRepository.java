package softeer.bemycarmaster.api.domain.trim.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrimJpaRepository extends JpaRepository<TrimEntity, Long> {

	@Query(value = "SELECT t FROM TrimEntity t WHERE t.model.id = :modelId")
	List<TrimEntity> findTrimsByModelId(Long modelId);
}
