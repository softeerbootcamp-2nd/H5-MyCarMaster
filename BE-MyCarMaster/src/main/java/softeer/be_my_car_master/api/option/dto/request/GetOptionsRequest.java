package softeer.be_my_car_master.api.option.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOptionsRequest {

	@Schema(description = "모델 식별자", example = "1")
	private Integer modelId;

	@Schema(description = "트림 식별자", example = "1")
	private Integer trimId;

	@Schema(description = "엔진 식별자", example = "1")
	private Integer engineId;
}
