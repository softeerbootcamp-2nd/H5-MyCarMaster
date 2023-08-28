package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.WheelDriveEntity;

public interface WheelDriveJpaRepository extends JpaRepository<WheelDriveEntity, Long> {
}
