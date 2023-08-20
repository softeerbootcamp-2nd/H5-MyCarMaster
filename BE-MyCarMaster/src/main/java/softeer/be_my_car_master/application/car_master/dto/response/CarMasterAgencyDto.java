package softeer.be_my_car_master.application.car_master.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarMasterAgencyDto {

	@Schema(description = "대리점 식별자", example = "1")
	private Long id;

	@Schema(description = "대리점 이름", example = "한양대지점")
	private String name;
}
