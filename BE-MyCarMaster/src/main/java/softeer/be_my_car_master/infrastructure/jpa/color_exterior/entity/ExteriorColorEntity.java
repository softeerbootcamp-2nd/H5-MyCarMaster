package softeer.be_my_car_master.infrastructure.jpa.color_exterior.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ModelEntity model;

	@OneToMany(mappedBy = "exteriorColor")
	private List<TrimExteriorColorEntity> trimExteriorColors;

	public ExteriorColor toExteriorColor(Long trimId) {
		TrimExteriorColorEntity trimExteriorColorEntity = trimExteriorColors.stream()
			.filter(trimExteriorColor -> trimExteriorColor.checkTrimId(trimId))
			.collect(Collectors.toList())
			.get(0);
		return ExteriorColor.builder()
			.id(id)
			.name(name)
			.colorImgUrl(colorImgUrl)
			.coloredImgUrl(trimExteriorColorEntity.getColoredCarImgUrl())
			.price(trimExteriorColorEntity.getPrice())
			.build();
	}
}
