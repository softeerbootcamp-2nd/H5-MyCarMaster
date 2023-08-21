package softeer.be_my_car_master.domain.estimate;

import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Estimate {

	private Long id;
	private UUID uuid;
	private Trim trim;
	private Engine engine;
	private WheelDrive wheelDrive;
	private BodyType bodyType;
	private ExteriorColor exteriorColor;
	private InteriorColor interiorColor;
	private List<Option> selectOptions;
	private List<Option> considerOptions;
}
