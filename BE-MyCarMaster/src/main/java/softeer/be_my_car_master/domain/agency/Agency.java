package softeer.be_my_car_master.domain.agency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor
public class Agency {

	private Long id;
	private String name;
	private String gu;
}
