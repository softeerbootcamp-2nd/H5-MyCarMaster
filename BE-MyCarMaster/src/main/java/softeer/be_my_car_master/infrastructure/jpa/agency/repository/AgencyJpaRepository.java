package softeer.be_my_car_master.infrastructure.jpa.agency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import softeer.be_my_car_master.infrastructure.jpa.agency.entity.AgencyEntity;

public interface AgencyJpaRepository extends JpaRepository<AgencyEntity, Long> {

	@Query(value = "SELECT a.* "
		+ "FROM agency a "
		+ "WHERE a.id IN ("
		+ "SELECT id "
		+ "FROM agency "
		+ "WHERE ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(longitude, latitude)) < 2000"
		+ ")",
		nativeQuery = true)
	List<AgencyEntity> findAllByLocation(
		@Param("latitude") Double latitude,
		@Param("longitude") Double longitude);

	List<AgencyEntity> findAllByGu(String gu);
}
