package softeer.be_my_car_master.infrastructure.body_type;

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
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.infrastructure.model.ModelEntity;

@Entity
@Table(name = "body_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BodyTypeEntity {

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

	public BodyType toBodyType() {
		return BodyType.builder()
			.id(id)
			.name(name)
			.description(description)
			.ratio(ratio)
			.price(price)
			.imgUrl(imgUrl)
			.build();
	}
}
