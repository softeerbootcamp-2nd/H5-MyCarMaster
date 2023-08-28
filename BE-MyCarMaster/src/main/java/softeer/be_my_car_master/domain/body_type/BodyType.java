package softeer.be_my_car_master.domain.body_type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
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

	public boolean isRightBodyType(Long bodyTypeId, Integer bodyTypePrice) {
		return id.equals(bodyTypeId) && price.equals(bodyTypePrice);
	}
}
