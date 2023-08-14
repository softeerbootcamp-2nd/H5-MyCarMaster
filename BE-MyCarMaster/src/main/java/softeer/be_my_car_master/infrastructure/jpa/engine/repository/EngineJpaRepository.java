package softeer.be_my_car_master.infrastructure.jpa.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.engine.entity.EngineEntity;

public interface EngineJpaRepository extends JpaRepository<EngineEntity, Long> {
}
