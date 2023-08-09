package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.TrimWheelDriveEntity;

public interface TrimWheelDriveJpaRepository extends JpaRepository<TrimWheelDriveEntity, Long> {

	@Query(value = "SELECT twd "
		+ "FROM TrimWheelDriveEntity twd "
		+ "JOIN FETCH twd.wheelDrive "
		+ "WHERE twd.trim.id = :trimId")
	List<TrimWheelDriveEntity> findAllByTrimId(Long trimId);
}
