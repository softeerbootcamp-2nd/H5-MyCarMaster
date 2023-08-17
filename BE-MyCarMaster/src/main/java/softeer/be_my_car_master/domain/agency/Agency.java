package softeer.be_my_car_master.domain.agency;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agency {

	private Long id;
	private String name;
	private String gu;
	private Double latitude;
	private Double longitude;
	private List<CarMaster> carMasters;
}
