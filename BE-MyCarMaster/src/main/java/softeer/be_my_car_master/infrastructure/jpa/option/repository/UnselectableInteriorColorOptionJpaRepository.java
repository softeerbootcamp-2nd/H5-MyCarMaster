package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.UnselectableInteriorColorOptionEntity;

public interface UnselectableInteriorColorOptionJpaRepository
	extends JpaRepository<UnselectableInteriorColorOptionEntity, Long> {

	@Query("SELECT uico.option.id "
		+ "FROM UnselectableInteriorColorOptionEntity uico "
		+ "WHERE uico.interiorColor.id = :interiorColorId")
	List<Long> findUnselectableOptionIdsByInteriorColorId(Long interiorColorId);
}
