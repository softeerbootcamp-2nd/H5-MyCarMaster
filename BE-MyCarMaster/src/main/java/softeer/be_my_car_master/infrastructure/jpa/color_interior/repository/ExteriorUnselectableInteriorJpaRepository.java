package softeer.be_my_car_master.infrastructure.jpa.color_interior.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.ExteriorUnselectableInteriorEntity;

public interface ExteriorUnselectableInteriorJpaRepository extends
	JpaRepository<ExteriorUnselectableInteriorEntity, Long> {

	@Query("SELECT eui.interiorColor.id "
		+ "FROM ExteriorUnselectableInteriorEntity eui "
		+ "WHERE eui.exteriorColor.id = :exteriorColorId")
	List<Long> findUnselectableInteriorColorIdsByExteriorColorId(Long exteriorColorId);
}
