package softeer.be_my_car_master.infrastructure.jpa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.model.Model;

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

	public Model toModel() {
		return Model.builder()
			.id(id)
			.name(name)
			.imgUrl(imgUrl)
			.build();
	}
}
