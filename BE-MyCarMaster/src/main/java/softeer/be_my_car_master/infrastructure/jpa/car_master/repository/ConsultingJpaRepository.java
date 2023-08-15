package softeer.be_my_car_master.infrastructure.jpa.car_master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.ConsultingEntity;

public interface ConsultingJpaRepository extends JpaRepository<ConsultingEntity, Long> {
}
