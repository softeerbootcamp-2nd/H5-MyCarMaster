package softeer.bemycarmaster.api.domain.trim.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Trim {

	private final Long id;
	private final String name;
	private final String description;
	private final Integer ratio;
	private final Integer price;
	private final String imgUrl;
}
