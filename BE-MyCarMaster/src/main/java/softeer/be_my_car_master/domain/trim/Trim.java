package softeer.be_my_car_master.domain.trim;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
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

	public boolean isRightTrim(Long trimId, Integer trimPrice) {
		return trimId.equals(id) && trimPrice.equals(price);
	}
}
