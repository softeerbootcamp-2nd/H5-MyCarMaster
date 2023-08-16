package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.RepresentativeOptionEntity;

public interface RepresentativeOptionJpaRepository extends JpaRepository<RepresentativeOptionEntity, Long> {

	@Query(value = "SELECT ro "
		+ "FROM RepresentativeOptionEntity ro "
		+ "JOIN FETCH ro.option "
		+ "WHERE ro.model.id = :modelId")
	List<RepresentativeOptionEntity> findAllByModelId(Long modelId);

	@Query(value = "SELECT ro "
		+ "FROM RepresentativeOptionEntity ro "
		+ "JOIN FETCH ro.appliedOption "
		+ "WHERE ro.model.id = :modelId AND ro.option.id IN :optionIds")
	List<RepresentativeOptionEntity> findAppliedOptionsByModelIdAndOptionIds(Long modelId, List<Long> optionIds);
}
