package softeer.be_my_car_master.infrastructure.jpa.agency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import softeer.be_my_car_master.infrastructure.jpa.agency.entity.AgencyEntity;

public interface AgencyJpaRepository extends JpaRepository<AgencyEntity, Long> {

	@Query(value = "SELECT * FROM agency "
		+ "WHERE ST_Contains(ST_Buffer(ST_GeomFromText(CONCAT('Point(', :lat, ' ', :long, ')'), 0), 0.021), points)",
		nativeQuery = true)
	List<AgencyEntity> findAllByLocation(@Param("lat") Double latitude, @Param("long") Double longitude);

	List<AgencyEntity> findAllByGu(String gu);
}
