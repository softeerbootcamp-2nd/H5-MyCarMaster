package softeer.bemycarmaster.api.domain.trim.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTrimsRequest {

	@Schema(description = "모델 식별자", example = "1")
	private Integer modelId;
}
