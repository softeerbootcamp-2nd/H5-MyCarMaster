package softeer.be_my_car_master.infrastructure.jpa.color_interior.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.InteriorColorEntity;

public interface InteriorColorJpaRepository extends JpaRepository<InteriorColorEntity, Long> {
}
