package softeer.be_my_car_master.domain.consulting;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.domain.car_master.CarMaster;
import softeer.be_my_car_master.domain.estimate.Estimate;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor
public class Consulting {

	private Long id;
	private String clientName;
	private String clientEmail;
	private String clientPhone;
	private Boolean isSent;
	private Estimate estimate;
	private CarMaster carMaster;

	public static Consulting create(
		String clientName,
		String clientEmail,
		String clientPhone,
		Estimate estimate,
		CarMaster carMaster
	) {
		return Consulting.builder()
			.carMaster(carMaster)
			.clientName(clientName)
			.clientEmail(clientEmail)
			.clientPhone(clientPhone)
			.estimate(estimate)
			.carMaster(carMaster)
			.build();
	}

	public UUID getUuid() {
		return estimate.getUuid();
	}
}
