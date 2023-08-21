package softeer.be_my_car_master.application.trim.dto.request;

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
public class GetTrimsRequest {

	@Schema(description = "모델 식별자", example = "1")
	@NotNull(message = "modelId는 Null일 수 없습니다.")
	@Min(value = 1, message = "modelId는 1 이상의 값입니다.")
	private Long modelId;
}
