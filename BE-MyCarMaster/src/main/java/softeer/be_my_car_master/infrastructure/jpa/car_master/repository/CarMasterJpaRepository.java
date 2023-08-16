package softeer.be_my_car_master.infrastructure.jpa.car_master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.CarMasterEntity;

public interface CarMasterJpaRepository extends JpaRepository<CarMasterEntity, Long> {
	@Query(value =
		"SELECT cm.* "
			+ "FROM car_master cm "
			+ "JOIN agency a ON cm.agency_id = a.id "
			+ "WHERE a.id IN ("
			+ "SELECT id "
			+ "FROM agency "
			+ "WHERE ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(longitude, latitude)) < 10000"
			+ ")"
			+ "ORDER BY "
			+ "ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(a.longitude, a.latitude)) ASC, "
			+ "cm.name ASC",
		nativeQuery = true
	)
	List<CarMasterEntity> findAllByAgencyLocationOrderByDistance(
		@Param("latitude") Double latitude,
		@Param("longitude") Double longitude
	);

	@Query(value =
		"SELECT cm.* "
			+ "FROM car_master cm "
			+ "JOIN agency a ON cm.agency_id = a.id "
			+ "WHERE a.id IN ("
			+ "SELECT id "
			+ "FROM agency "
			+ "WHERE ST_Distance_Sphere(POINT(:longitude, :latitude), POINT(longitude, latitude)) < 10000"
			+ ")"
			+ "ORDER BY cm.sales DESC, cm.name ASC",
		nativeQuery = true
	)
	List<CarMasterEntity> findAllByAgencyLocationOrderBySales(
		@Param("latitude") Double latitude,
		@Param("longitude") Double longitude
	);
}
