package softeer.be_my_car_master.infrastructure.jpa.color_interior.entity;

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
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

@Entity
@Table(name = "interior_color")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InteriorColorEntity {

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

	@OneToMany(mappedBy = "interiorColor")
	private List<TrimInteriorColorEntity> trimInteriorColors;

	public InteriorColor toInteriorColor(Long trimId) {
		TrimInteriorColorEntity trimInteriorColorEntity = trimInteriorColors.stream()
			.filter(trimInteriorColor -> trimInteriorColor.checkTrimId(trimId))
			.collect(Collectors.toList())
			.get(0);
		return InteriorColor.builder()
			.id(id)
			.name(name)
			.colorImgUrl(colorImgUrl)
			.price(trimInteriorColorEntity.getPrice())
			.build();
	}
}
