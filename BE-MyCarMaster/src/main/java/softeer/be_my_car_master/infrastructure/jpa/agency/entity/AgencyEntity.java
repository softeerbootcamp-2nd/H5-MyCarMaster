package softeer.be_my_car_master.infrastructure.jpa.agency.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.infrastructure.jpa.car_master.entity.CarMasterEntity;

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

	@OneToMany(mappedBy = "agency")
	private List<CarMasterEntity> carMasterEntities;

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
