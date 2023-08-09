package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.UnselectableWheelDriveOptionEntity;

public interface UnselectableWheelDriveOptionJpaRepository extends
	JpaRepository<UnselectableWheelDriveOptionEntity, Long> {

	@Query("SELECT uwdo.option.id "
		+ "FROM UnselectableWheelDriveOptionEntity uwdo "
		+ "WHERE uwdo.wheelDrive.id = :wheelDriveId")
	List<Long> findUnselectableOptionIdsByWheelDriveId(Long wheelDriveId);
}
