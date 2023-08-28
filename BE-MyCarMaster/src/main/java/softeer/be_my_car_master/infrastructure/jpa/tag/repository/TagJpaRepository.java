package softeer.be_my_car_master.infrastructure.jpa.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.infrastructure.jpa.tag.entity.TagEntity;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {

	@Query("SELECT t.name FROM TagEntity t WHERE t.isMultiSelectable = false")
	List<String> findSingleSelectableTagIdsByIsMultiSelectable();
}
