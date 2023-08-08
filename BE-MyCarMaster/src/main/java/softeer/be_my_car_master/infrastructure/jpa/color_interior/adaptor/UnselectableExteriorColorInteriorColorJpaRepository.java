package softeer.be_my_car_master.infrastructure.jpa.color_interior.adaptor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.UnselectableExteriorColorInteriorColorEntity;

public interface UnselectableExteriorColorInteriorColorJpaRepository extends
	JpaRepository<UnselectableExteriorColorInteriorColorEntity, Long> {

	@Query("SELECT uecic.interiorColor.id "
		+ "FROM UnselectableExteriorColorInteriorColorEntity uecic "
		+ "WHERE uecic.exteriorColor.id = :exteriorColorId")
	List<Long> findAllByExteriorColorId(Long exteriorColorId);
}
