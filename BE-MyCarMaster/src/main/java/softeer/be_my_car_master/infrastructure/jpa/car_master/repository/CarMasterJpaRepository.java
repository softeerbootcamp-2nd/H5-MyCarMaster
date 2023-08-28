package softeer.be_my_car_master.infrastructure.jpa.car_master.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.CarMasterEntity;

public interface CarMasterJpaRepository extends JpaRepository<CarMasterEntity, Long> {

	@Query(value = "SELECT cm "
		+ "FROM CarMasterEntity cm "
		+ "JOIN FETCH cm.agency a "
		+ "WHERE a.id = :agencyId")
	List<CarMasterEntity> findAllByAgency(Long agencyId);

	@Query(value = "SELECT cm "
		+ "FROM CarMasterEntity cm "
		+ "JOIN FETCH cm.agency a "
		+ "WHERE a.id IN :agencyIds "
		+ "ORDER BY cm.sales DESC, cm.name ASC")
	List<CarMaster> findAllByAgencyIdsOrderBySales(List<Long> agencyIds);

	@Query(value = "SELECT cm.id "
		+ "FROM CarMasterEntity cm "
		+ "WHERE cm.email= :email "
		+ "AND cm.phone = :phone")
	Optional<Long> findIdByForm(String email, String phone);
}
