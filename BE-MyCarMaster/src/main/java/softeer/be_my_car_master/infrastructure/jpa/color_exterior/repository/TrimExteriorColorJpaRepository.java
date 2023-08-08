package softeer.be_my_car_master.infrastructure.jpa.color_exterior.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity.TrimExteriorColorEntity;

public interface TrimExteriorColorJpaRepository extends JpaRepository<TrimExteriorColorEntity, Long> {

	@Query(value = "SELECT tec "
		+ "FROM TrimExteriorColorEntity tec "
		+ "JOIN FETCH tec.exteriorColor "
		+ "WHERE tec.trim.id = :trimId")
	List<TrimExteriorColorEntity> findAllByTrimId(Long trimId);
}
