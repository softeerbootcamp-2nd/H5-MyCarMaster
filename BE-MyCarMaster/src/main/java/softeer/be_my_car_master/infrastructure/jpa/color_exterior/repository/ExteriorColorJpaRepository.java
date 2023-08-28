package softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.ExteriorColorEntity;

public interface ExteriorColorJpaRepository extends JpaRepository<ExteriorColorEntity, Long> {
}
