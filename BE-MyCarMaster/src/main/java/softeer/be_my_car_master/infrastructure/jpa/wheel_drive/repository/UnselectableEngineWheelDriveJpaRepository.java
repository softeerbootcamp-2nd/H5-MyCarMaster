package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.UnselectableEngineWheelDriveEntity;

public interface UnselectableEngineWheelDriveJpaRepository
	extends JpaRepository<UnselectableEngineWheelDriveEntity, Long> {

	@Query("SELECT uew.wheelDrive.id "
		+ "FROM UnselectableEngineWheelDriveEntity uew "
		+ "WHERE uew.engine.id = :engineId")
	List<Long> findUnselectableWheelDriveIdsByEngineId(Long engineId);
}
