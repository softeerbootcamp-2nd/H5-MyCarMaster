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
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.infrastructure.jpa.store.entity.StoreEntity;

@Entity
@Table(name = "car_master")
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
	@JoinColumn(name = "store_id")
	private StoreEntity store;
}
