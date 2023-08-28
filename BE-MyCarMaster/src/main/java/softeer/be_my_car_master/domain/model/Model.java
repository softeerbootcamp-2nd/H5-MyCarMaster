package softeer.be_my_car_master.domain.model;

import java.time.LocalDateTime;

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
	private final Integer price;
	private final Type type;
	private final LocalDateTime createdAt;

	public boolean isRightModel(Long modelId) {
		return modelId.equals(id);
	}

	public String getType() {
		return type.getValue();
	}
}
