package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.UnselectableBodyTypeOptionEntity;

public interface UnselectableBodyTypeOptionJpaRepository extends JpaRepository<UnselectableBodyTypeOptionEntity, Long> {

	@Query("SELECT ubo.option.id "
		+ "FROM UnselectableBodyTypeOptionEntity ubo "
		+ "WHERE ubo.bodyType.id = :bodyTypeId")
	List<Long> findUnselectableOptionIdsByBodyTypeId(Long bodyTypeId);
}
