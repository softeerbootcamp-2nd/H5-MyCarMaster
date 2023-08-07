package softeer.bemycarmaster.api.domain.trim.infrastructure;

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
import softeer.bemycarmaster.api.domain.model.infrastructure.ModelEntity;
import softeer.bemycarmaster.api.domain.trim.domain.Trim;

@Entity
@Table(name = "trim")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrimEntity {

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

	public Trim toTrim() {
		return Trim.builder()
			.id(id)
			.name(name)
			.description(description)
			.ratio(ratio)
			.price(price)
			.imgUrl(imgUrl)
			.build();
	}
}
