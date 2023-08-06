package softeer.bemycarmaster.api.domain.wheeldrive.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetWheelDrivesRequest {

	@Schema(description = "트림 식별자", example = "1")
	private Integer trimId;
}
