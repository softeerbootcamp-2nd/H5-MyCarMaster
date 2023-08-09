package softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;

@Entity
public class TrimExteriorColorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ratio")
	private Integer ratio;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "colored_car_img_url", nullable = false)
	private String coloredCarImgUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trim_id")
	private TrimEntity trim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exterior_color_id")
	private ExteriorColorEntity exteriorColor;

	public ExteriorColor toExteriorColor() {
		return ExteriorColor.builder()
			.id(exteriorColor.getId())
			.name(exteriorColor.getName())
			.ratio(ratio)
			.price(price)
			.colorImgUrl(exteriorColor.getColorImgUrl())
			.coloredImgUrl(coloredCarImgUrl)
			.build();
	}
}
