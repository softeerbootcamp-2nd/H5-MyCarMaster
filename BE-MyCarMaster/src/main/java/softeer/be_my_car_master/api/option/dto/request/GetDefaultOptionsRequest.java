package softeer.be_my_car_master.api.option.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDefaultOptionsRequest {

	@Schema(description = "트림 식별자", example = "1")
	@NotNull(message = "trimId는 Null일 수 없습니다.")
	@Min(value = 1, message = "trimId는 1 이상의 값입니다.")
	private Long trimId;

	@Schema(description = "엔진 식별자", example = "1")
	@NotNull(message = "engineId는 Null일 수 없습니다.")
	@Min(value = 1, message = "engineId는 1 이상의 값입니다.")
	private Long engineId;

	@Schema(description = "구동 방식 식별자", example = "1")
	@NotNull(message = "wheelDriveId는 Null일 수 없습니다.")
	@Min(value = 1, message = "wheelDriveId는 1 이상의 값입니다.")
	private Long wheelDriveId;

	@Schema(description = "바디 타입 식별자", example = "1")
	@NotNull(message = "bodyTypeId는 Null일 수 없습니다.")
	@Min(value = 1, message = "bodyTypeId는 1 이상의 값입니다.")
	private Long bodyTypeId;
}
