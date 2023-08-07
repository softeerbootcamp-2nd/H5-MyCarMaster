package softeer.bemycarmaster.api.domain.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Model {

	private final Long id;
	private final String name;
	private final String imgUrl;
}
