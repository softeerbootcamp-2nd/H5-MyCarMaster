package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.AdditionalTrimOptionEntity;

public interface AdditionalTrimOptionJpaRepository extends JpaRepository<AdditionalTrimOptionEntity, Long> {

	@Query(value = "SELECT ato "
		+ "FROM AdditionalTrimOptionEntity ato "
		+ "JOIN FETCH ato.option o "
		+ "JOIN FETCH o.tag "
		+ "WHERE ato.trim.id = :trimId")
	List<AdditionalTrimOptionEntity> findAllByTrimId(Long trimId);
}
