package softeer.be_my_car_master.application.color_interior.dto.request;

import javax.validation.constraints.Min;
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
public class GetInteriorColorsRequest {

	@Schema(description = "트림 식별자", example = "1")
	@NotNull(message = "trimId는 Null 일 수 없습니다.")
	@Min(value = 1, message = "trimId는 1 이상의 값입니다.")
	private Long trimId;

	@Schema(description = "외장 색상 식별자", example = "1")
	@NotNull(message = "exteriorColorId는 Null 일 수 없습니다.")
	@Min(value = 1, message = "exteriorColorId는 1 이상의 값입니다.")
	private Long exteriorColorId;
}
