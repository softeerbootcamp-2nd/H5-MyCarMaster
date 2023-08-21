package softeer.be_my_car_master.application.estimate.dto.request;

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
public class EstimateOptionRequestDto {

	@Schema(description = "옵션 식별자", example = "1")
	@NotNull(message = "옵션 id는 Null일 수 없습니다.")
	@Min(value = 1, message = "옵션 id는 1 이상의 값입니다.")
	private Long id;

	@Schema(description = "옵션 가격", example = "100000")
	@NotNull(message = "옵션 price는 Null일 수 없습니다.")
	@Range(min = 0, max = 2000000000, message = "옵션 price는 0 이상 ~ 2,000,000,000 이하의 값입니다.")
	private Integer price;
}
