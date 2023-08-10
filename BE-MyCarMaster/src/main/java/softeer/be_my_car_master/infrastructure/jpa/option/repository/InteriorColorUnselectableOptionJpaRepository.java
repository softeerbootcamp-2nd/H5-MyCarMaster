package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.InteriorColorUnselectableOptionEntity;

public interface InteriorColorUnselectableOptionJpaRepository
	extends JpaRepository<InteriorColorUnselectableOptionEntity, Long> {

	@Query("SELECT icuo.option.id "
		+ "FROM InteriorColorUnselectableOptionEntity icuo "
		+ "WHERE icuo.interiorColor.id = :interiorColorId")
	List<Long> findUnselectableOptionIdsByInteriorColorId(Long interiorColorId);
}
