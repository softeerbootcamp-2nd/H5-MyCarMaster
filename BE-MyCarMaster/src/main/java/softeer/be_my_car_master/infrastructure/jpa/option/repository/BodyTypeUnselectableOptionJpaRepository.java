package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.BodyTypeUnselectableOptionEntity;

public interface BodyTypeUnselectableOptionJpaRepository extends JpaRepository<BodyTypeUnselectableOptionEntity, Long> {

	@Query("SELECT btuo.option.id "
		+ "FROM BodyTypeUnselectableOptionEntity btuo "
		+ "WHERE btuo.bodyType.id = :bodyTypeId")
	List<Long> findUnselectableOptionIdsByBodyTypeId(Long bodyTypeId);
}
