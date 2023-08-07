package softeer.bemycarmaster.api.domain.model.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelJpaRepository extends JpaRepository<ModelEntity, Long> {
}
