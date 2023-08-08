package softeer.be_my_car_master.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor
public class Model {

	private final Long id;
	private final String name;
	private final String imgUrl;
}
