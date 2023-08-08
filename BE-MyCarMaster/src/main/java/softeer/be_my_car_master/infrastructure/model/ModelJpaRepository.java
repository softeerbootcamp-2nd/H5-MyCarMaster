package softeer.be_my_car_master.infrastructure.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelJpaRepository extends JpaRepository<ModelEntity, Long> {
}
