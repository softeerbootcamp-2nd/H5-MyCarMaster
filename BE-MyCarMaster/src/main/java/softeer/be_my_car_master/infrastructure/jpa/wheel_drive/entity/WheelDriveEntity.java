package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity;

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
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

@Entity
@Table(name = "wheel_drive")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WheelDriveEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "ratio", nullable = false)
	private Integer ratio;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "img_url", nullable = false)
	private String imgUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ModelEntity model;

	public WheelDrive toWheelDrive() {
		return WheelDrive.builder()
			.id(id)
			.name(name)
			.description(description)
			.ratio(ratio)
			.price(price)
			.imgUrl(imgUrl)
			.build();
	}
}
