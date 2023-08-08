package softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;

@Entity
@Table(name = "exterior_color")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExteriorColorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "color_img_url", nullable = false)
	private String colorImgUrl;

	public ExteriorColor toExteriorColor() {
		return ExteriorColor.builder()
			.id(id)
			.name(name)
			.colorImgUrl(colorImgUrl)
			.build();
	}
}
