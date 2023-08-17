package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;

public interface TrimAdditionalOptionJpaRepository extends JpaRepository<TrimAdditionalOptionEntity, Long> {

	@Query(value = "SELECT tao "
		+ "FROM TrimAdditionalOptionEntity tao "
		+ "JOIN FETCH tao.option o "
		+ "WHERE tao.trim.id = :trimId")
	List<TrimAdditionalOptionEntity> findAllByTrimId(Long trimId);

	@Query("SELECT tao.option.id "
		+ "FROM TrimAdditionalOptionEntity tao "
		+ "WHERE tao.trim.id = :trimId")
	List<Long> findUnselectableOptionIdsByTrimId(Long trimId);

	@Query("SELECT tao "
		+ "FROM TrimAdditionalOptionEntity tao "
		+ "JOIN FETCH tao.option o "
		+ "WHERE tao.trim.id = :trimId AND tao.option.id IN :optionIds")
	List<TrimAdditionalOptionEntity> findAllByIdIn(Long trimId, List<Long> optionIds);

	@Query(value = "SELECT tao.trim.id "
		+ "FROM TrimAdditionalOptionEntity tao "
		+ "WHERE tao.option.id = :optionId")
	List<Long> findTrimIdsByOptionId(Long optionId);
}
