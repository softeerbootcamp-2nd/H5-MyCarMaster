package softeer.be_my_car_master.application.car_master.dto.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarMasterRequest {

	@Schema(description = "위도", example = "32.32132")
	@NotNull(message = "latitude는 Null 일 수 없습니다.")
	@DecimalMin(value = "-90.000000", message = "위도는 -90.0 이상의 값입니다.")
	@DecimalMax(value = "90.000000", message = "위도는 90.0 이하의 값입니다.")
	private Double latitude;

	@Schema(description = "경도", example = "127.32132")
	@NotNull(message = "longitude는 Null 일 수 없습니다.")
	@DecimalMin(value = "-180.000000", message = "경도는 -180.0 이상의 값입니다.")
	@DecimalMax(value = "180.000000", message = "경도는 180.0 이하의 값입니다.")
	private Double longitude;
}
