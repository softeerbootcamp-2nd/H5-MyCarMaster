package softeer.be_my_car_master.domain.engine;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

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
	private ModelEntity model;
}
