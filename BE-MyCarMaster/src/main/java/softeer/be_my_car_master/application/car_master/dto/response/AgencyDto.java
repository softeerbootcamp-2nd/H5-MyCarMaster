package softeer.be_my_car_master.application.car_master.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.agency.Agency;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDto {

	private Long id;
	private String name;
	private String gu;
	private Double latitude;
	private Double longitude;

	public static AgencyDto from(Agency agency) {
		return AgencyDto.builder()
			.id(agency.getId())
			.name(agency.getName())
			.gu(agency.getGu())
			.latitude(agency.getLatitude())
			.longitude(agency.getLongitude())
			.build();
	}
}
