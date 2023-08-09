package softeer.be_my_car_master.infrastructure.jpa.engine.entity;

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
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

@Entity
@Table(name = "engine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EngineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "ratio")
	private Integer ratio;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "img_url", nullable = false)
	private String imgUrl;

	@Column(name = "fuel_min", nullable = false)
	private Double fuelMin;

	@Column(name = "fuel_max", nullable = false)
	private Double fuelMax;

	@Column(name = "power", nullable = false)
	private Integer power;

	@Column(name = "toque", nullable = false)
	private Double toque;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ModelEntity model;

	public Engine toEngine() {
		return Engine.builder()
			.id(id)
			.name(name)
			.description(description)
			.ratio(ratio)
			.price(price)
			.imgUrl(imgUrl)
			.fuelMin(fuelMin)
			.fuelMax(fuelMax)
			.power(power)
			.build();
	}
}
