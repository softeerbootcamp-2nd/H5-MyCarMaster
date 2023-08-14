package softeer.be_my_car_master.domain.color_interior;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InteriorColor {

	private Long id;
	private String name;
	private Integer price;
	private Integer ratio;
	private String colorImgUrl;
	private String coloredImgUrl;

	public boolean isSelectable(List<Long> unselectableColorIds) {
		return !unselectableColorIds.contains(id);
	}

	public boolean isRightInteriorColor(Long interiorColorId, Integer interiorColorPrice) {
		return interiorColorId.equals(id) && interiorColorPrice.equals(price);
	}
}
