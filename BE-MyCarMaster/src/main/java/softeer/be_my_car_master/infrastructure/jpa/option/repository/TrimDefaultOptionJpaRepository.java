package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimDefaultOptionEntity;

public interface TrimDefaultOptionJpaRepository extends JpaRepository<TrimDefaultOptionEntity, Long> {

	@Query(value = "SELECT tdo "
		+ "FROM TrimDefaultOptionEntity tdo "
		+ "JOIN FETCH tdo.option o "
		+ "WHERE tdo.trim.id = :trimId")
	List<TrimDefaultOptionEntity> findAllByTrimId(Long trimId);

	@Query(value = "SELECT tdo.trim.id "
		+ "FROM TrimDefaultOptionEntity tdo "
		+ "WHERE tdo.option.id = :optionId")
	List<Long> findTrimIdsByOptionId(Long optionId);
}
