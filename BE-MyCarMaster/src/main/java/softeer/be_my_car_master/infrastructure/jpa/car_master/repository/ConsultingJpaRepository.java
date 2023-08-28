package softeer.be_my_car_master.infrastructure.jpa.car_master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.ConsultingEntity;

public interface ConsultingJpaRepository extends JpaRepository<ConsultingEntity, Long> {

	@Query(value = "SELECT c "
		+ "FROM ConsultingEntity c "
		+ "JOIN FETCH c.estimate "
		+ "WHERE c.isSent = :isSent")
	List<ConsultingEntity> findAllByIsSent(boolean isSent);

	@Query(value = "SELECT c "
		+ "FROM ConsultingEntity c "
		+ "JOIN FETCH c.estimate "
		+ "WHERE c.carMaster.id = :carMasterId")
	List<Consulting> findAllByCarMaster(Long carMasterId);
}
