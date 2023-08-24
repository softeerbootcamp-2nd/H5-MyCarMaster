package softeer.be_my_car_master.infrastructure.jpa.agency.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Point;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.agency.Agency;

@Entity
@Getter
@Table(name = "agency")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgencyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "latitude", nullable = false)
	private Double latitude;

	@Column(name = "longitude", nullable = false)
	private Double longitude;

	@Column(name = "gu", nullable = false)
	private String gu;

	@Column(name = "points")
	private Point points;

	public Agency toAgency() {
		return Agency.builder()
			.id(id)
			.name(name)
			.latitude(latitude)
			.longitude(longitude)
			.gu(gu)
			.build();
	}

	public Agency toAgencyInGu() {
		return Agency.builder()
			.id(id)
			.name(name)
			.latitude(latitude)
			.longitude(longitude)
			.gu(gu)
			.build();
	}
}
