package softeer.be_my_car_master.infrastructure.jpa.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.domain.model.Type;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;

@Entity
@Table(name = "model")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "img_url", nullable = false)
	private String imgUrl;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	@OneToMany(mappedBy = "model")
	private List<TrimEntity> trims = new ArrayList<>();

	public Model toModel() {
		Integer minPrice = trims.stream()
			.map(TrimEntity::getPrice)
			.min(Integer::compareTo)
			.orElse(0);

		return Model.builder()
			.id(id)
			.name(name)
			.imgUrl(imgUrl)
			.type(type)
			.price(minPrice)
			.build();
	}
}
