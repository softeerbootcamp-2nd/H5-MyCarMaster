package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimDefaultOptionEntity;

public interface TrimDefaultOptionJpaRepository extends JpaRepository<TrimDefaultOptionEntity, Long> {

	@Query(value = "SELECT tao "
		+ "FROM TrimDefaultOptionEntity tao "
		+ "JOIN FETCH tao.option o "
		+ "WHERE tao.trim.id = :trimId")
	List<TrimDefaultOptionEntity> findAllByTrimId(Long trimId);
}
