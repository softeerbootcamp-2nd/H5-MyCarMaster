package softeer.be_my_car_master.infrastructure.jpa.car_master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.infrastructure.jpa.agency.entity.AgencyEntity;

@Entity
@Table(name = "car_master")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarMasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "img_url", nullable = false)
	private String imgUrl;

	@Column(name = "intro", nullable = false)
	private String intro;

	@Column(name = "sales", nullable = false)
	private Integer sales;

	@Column(name = "email", nullable = false)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private AgencyEntity agency;

	public CarMaster toCarMaster() {
		return new CarMaster(id);
	}

	public static CarMasterEntity from(CarMaster carMaster) {
		return CarMasterEntity.builder()
			.id(carMaster.getId())
			.build();
	}
}
