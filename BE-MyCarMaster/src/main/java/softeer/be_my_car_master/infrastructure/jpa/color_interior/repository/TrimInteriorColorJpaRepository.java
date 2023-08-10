package softeer.be_my_car_master.infrastructure.jpa.color_interior.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.color_interior.entity.TrimInteriorColorEntity;

public interface TrimInteriorColorJpaRepository extends JpaRepository<TrimInteriorColorEntity, Long> {

	@Query(value = "SELECT tic "
		+ "FROM TrimInteriorColorEntity tic "
		+ "JOIN FETCH tic.interiorColor "
		+ "WHERE tic.trim.id = :trimId")
	List<TrimInteriorColorEntity> findAllByTrimId(Long trimId);
}
