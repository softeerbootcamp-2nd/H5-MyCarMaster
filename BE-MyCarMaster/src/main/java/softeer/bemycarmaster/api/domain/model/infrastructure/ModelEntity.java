package softeer.bemycarmaster.api.domain.model.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import softeer.bemycarmaster.api.domain.model.domain.Model;

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
