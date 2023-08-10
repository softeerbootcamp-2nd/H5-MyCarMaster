package softeer.be_my_car_master.api.engine.dto.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
public class GetUnselectableOptionsByEngineRequest {

	@Schema(description = "트림 식별자", example = "1")
	@NotNull(message = "trimId는 Null일 수 없습니다.")
	@Min(value = 1, message = "trimId는 1 이상의 값입니다.")
	private Long trimId;

	@Schema(description = "기존에 선택된 옵션 식별자 목록", example = "[1, 2, 3]")
	@NotEmpty(message = "옵션 식별자 목록은 비어있을 수 없습니다.")
	private List<Long> optionsIds;
}
