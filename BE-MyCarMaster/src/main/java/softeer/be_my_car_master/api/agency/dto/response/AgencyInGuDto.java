package softeer.be_my_car_master.api.agency.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class AgencyInGuDto {

	@Schema(description = "대리점 식별자", example = "1")
	private Long id;

	@Schema(description = "대리점 이름", example = "햔양 대리점")
	private String name;

	@Schema(description = "위도", example = "32.1232")
	private Double latitude;

	@Schema(description = "경도", example = "127.2332")
	private Double longitude;

	public static AgencyInGuDto from(Agency agency) {
		return AgencyInGuDto.builder()
			.id(agency.getId())
			.name(agency.getName())
			.latitude(agency.getLatitude())
			.longitude(agency.getLongitude())
			.build();
	}
}
