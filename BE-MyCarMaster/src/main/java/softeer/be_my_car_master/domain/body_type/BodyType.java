package softeer.be_my_car_master.domain.body_type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BodyType {

	private final Long id;
	private final String name;
	private final String description;
	private final Integer ratio;
	private final Integer price;
	private final String imgUrl;
}
