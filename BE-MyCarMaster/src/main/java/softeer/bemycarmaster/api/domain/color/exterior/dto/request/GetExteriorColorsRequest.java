package softeer.bemycarmaster.api.domain.color.exterior.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetExteriorColorsRequest {

	@Schema(description = "트림 식별자", example = "1")
	@NotNull(message = "trimId는 Null 일 수 없습니다.")
	@Min(value = 1, message = "trimId는 1 이상의 값입니다.")
	private Integer trimId;
}
