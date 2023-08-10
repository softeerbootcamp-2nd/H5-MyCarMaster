package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.WheelDriveUnselectableOptionEntity;

public interface WheelDriveUnselectableOptionJpaRepository extends
	JpaRepository<WheelDriveUnselectableOptionEntity, Long> {

	@Query("SELECT wduo.option.id "
		+ "FROM WheelDriveUnselectableOptionEntity wduo "
		+ "WHERE wduo.wheelDrive.id = :wheelDriveId")
	List<Long> findUnselectableOptionIdsByWheelDriveId(Long wheelDriveId);
}
