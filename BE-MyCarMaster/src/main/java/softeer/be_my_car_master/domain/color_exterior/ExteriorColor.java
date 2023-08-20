package softeer.be_my_car_master.domain.color_exterior;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExteriorColor {

	private Long id;
	private String name;
	private Integer price;
	private Integer ratio;
	private String colorImgUrl;
	private String coloredImgUrl;

	public boolean isRightExteriorColor(Long exteriorColorId, Integer exteriorColorPrice) {
		return id.equals(exteriorColorId) && price.equals(exteriorColorPrice);
	}
}
