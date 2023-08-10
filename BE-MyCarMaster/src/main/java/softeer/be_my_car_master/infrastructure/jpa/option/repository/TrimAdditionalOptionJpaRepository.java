package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;

public interface TrimAdditionalOptionJpaRepository extends JpaRepository<TrimAdditionalOptionEntity, Long> {

	@Query(value = "SELECT tao "
		+ "FROM TrimAdditionalOptionEntity tao "
		+ "JOIN FETCH tao.option o "
		+ "JOIN FETCH o.tag "
		+ "WHERE tao.trim.id = :trimId")
	List<TrimAdditionalOptionEntity> findAllByTrimId(Long trimId);
}
