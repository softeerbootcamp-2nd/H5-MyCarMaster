package softeer.be_my_car_master.domain.color_interior;

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
}
