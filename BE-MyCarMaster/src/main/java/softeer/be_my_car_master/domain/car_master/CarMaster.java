package softeer.be_my_car_master.domain.car_master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor
public class CarMaster {

	private Long id;
	private String name;
	private String phone;
	private String imgUrl;
	private String intro;
	private Integer sales;
	private String email;
	private Agency agency;

	public String getAgencyName() {
		return agency.getName();
	}
}
