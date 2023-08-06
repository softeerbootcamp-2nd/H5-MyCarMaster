package softeer.bemycarmaster.api.domain.color.interior.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInteriorColorsRequest {

	@Schema(description = "트림 식별자", example = "1")
	private Integer trimId;

	@Schema(description = "외장 색상 식별자", example = "1")
	private Integer exteriorColorId;
}
