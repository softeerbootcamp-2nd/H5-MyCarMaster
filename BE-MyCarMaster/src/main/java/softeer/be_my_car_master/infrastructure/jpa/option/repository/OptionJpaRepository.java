package softeer.be_my_car_master.infrastructure.jpa.option.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;

public interface OptionJpaRepository extends JpaRepository<OptionEntity, Long> {

	List<OptionEntity> findAllByIdIn(List<Long> ids);
}
