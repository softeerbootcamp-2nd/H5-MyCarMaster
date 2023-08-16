package softeer.be_my_car_master.api.agency.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDto {

	@Schema(description = "대리점 식별자", example = "1")
	private Long id;

	@Schema(description = "대리점 이름", example = "햔양 대리점")
	private String name;
}
