package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.EngineUnselectableWheelDriveEntity;

public interface EngineUnselectableWheelDriveJpaRepository
	extends JpaRepository<EngineUnselectableWheelDriveEntity, Long> {

	@Query("SELECT euwd.wheelDrive.id "
		+ "FROM EngineUnselectableWheelDriveEntity euwd "
		+ "WHERE euwd.engine.id = :engineId")
	List<Long> findUnselectableWheelDriveIdsByEngineId(Long engineId);
}
