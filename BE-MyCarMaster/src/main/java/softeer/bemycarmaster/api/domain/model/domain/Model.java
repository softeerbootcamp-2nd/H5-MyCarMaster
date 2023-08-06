package softeer.bemycarmaster.api.domain.model.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Model {

	private final Long id;
	private final String name;
	private final String imgUrl;

	@Builder
	private Model(Long id, String name, String imgUrl) {
		this.id = id;
		this.name = name;
		this.imgUrl = imgUrl;
	}
}
