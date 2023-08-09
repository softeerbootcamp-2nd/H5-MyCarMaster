package softeer.be_my_car_master.infrastructure.jpa.color_interior.adaptor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.UnselectableExteriorInteriorEntity;

public interface UnselectableExteriorInteriorJpaRepository extends
	JpaRepository<UnselectableExteriorInteriorEntity, Long> {

	@Query("SELECT uei.interiorColor.id "
		+ "FROM UnselectableExteriorInteriorEntity uei "
		+ "WHERE uei.exteriorColor.id = :exteriorColorId")
	List<Long> findUnselectableInteriorColorIdByExteriorColorId(Long exteriorColorId);
}
