package softeer.be_my_car_master.domain.engine;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Engine {

	private Long id;
	private String name;
	private String description;
	private Integer ratio;
	private Integer price;
	private String imgUrl;
	private Double fuelMin;
	private Double fuelMax;
	private Integer power;
	private Double toque;

	public boolean isRightEngine(Long engineId, Integer enginePrice) {
		return engineId.equals(id) && enginePrice.equals(price);
	}
}
