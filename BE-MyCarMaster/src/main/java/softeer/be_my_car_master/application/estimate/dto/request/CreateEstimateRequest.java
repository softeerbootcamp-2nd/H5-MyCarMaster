package softeer.be_my_car_master.application.estimate.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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
public class CreateEstimateRequest {

	@Schema(description = "모델 식별자", example = "1")
	@NotNull(message = "modelId는 Null일 수 없습니다.")
	@Min(value = 1, message = "modelId는 1 이상의 값입니다.")
	private Long modelId;

	@Schema(description = "트림 식별자", example = "1")
	@NotNull(message = "trimId는 Null일 수 없습니다.")
	@Min(value = 1, message = "trimId는 1 이상의 값입니다.")
	private Long trimId;

	@Schema(description = "트림 가격", example = "100000")
	@NotNull(message = "trimPrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "trimPrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer trimPrice;

	@Schema(description = "엔진 식별자", example = "1")
	@NotNull(message = "engineId는 Null일 수 없습니다.")
	@Min(value = 1, message = "engineId는 1 이상의 값입니다.")
	private Long engineId;

	@Schema(description = "엔진 가격", example = "100000")
	@NotNull(message = "enginePrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "enginePrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer enginePrice;

	@Schema(description = "구동 방식 식별자", example = "1")
	@NotNull(message = "wheelDriveId는 Null일 수 없습니다.")
	@Min(value = 1, message = "wheelDriveId는 1 이상의 값입니다.")
	private Long wheelDriveId;

	@Schema(description = "구동 방식 가격", example = "100000")
	@NotNull(message = "wheelDrivePrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "wheelDrivePrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer wheelDrivePrice;

	@Schema(description = "바디 타입 식별자", example = "1")
	@NotNull(message = "bodyTypeId는 Null일 수 없습니다.")
	@Min(value = 1, message = "bodyTypeId는 1 이상의 값입니다.")
	private Long bodyTypeId;

	@Schema(description = "바디 타입 가격", example = "100000")
	@NotNull(message = "bodyTypePrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "bodyTypePrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer bodyTypePrice;

	@Schema(description = "외장 색상 식별자", example = "1")
	@NotNull(message = "ExteriorColorId는 Null일 수 없습니다.")
	@Min(value = 1, message = "ExteriorColorId는 1 이상의 값입니다.")
	private Long exteriorColorId;

	@Schema(description = "외장 색상 가격", example = "100000")
	@NotNull(message = "exteriorColorPrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "exteriorColorPrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer exteriorColorPrice;

	@Schema(description = "내장 색상 식별자", example = "1")
	@NotNull(message = "InteriorColorId는 Null일 수 없습니다.")
	@Min(value = 1, message = "InteriorColorId는 1 이상의 값입니다.")
	private Long interiorColorId;

	@Schema(description = "내장 색상 가격", example = "100000")
	@NotNull(message = "interiorColorPrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "interiorColorPrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer interiorColorPrice;

	@Valid
	private List<EstimateOptionRequestDto> selectOptions;

	@Schema(description = "선택 옵션 가격", example = "100000")
	@Range(min = 0, max = 2000000000, message = "additionalOptionPrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer selectOptionPrice;

	@Valid
	private List<EstimateOptionRequestDto> considerOptions;

	@Schema(description = "전체 가격", example = "100000")
	@NotNull(message = "totalPrice는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "totalPrice는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer totalPrice;
}
