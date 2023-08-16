package softeer.be_my_car_master.domain.estimate;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@AllArgsConstructor
public class Estimate {

	private Long id;
	private UUID uuid;
}
