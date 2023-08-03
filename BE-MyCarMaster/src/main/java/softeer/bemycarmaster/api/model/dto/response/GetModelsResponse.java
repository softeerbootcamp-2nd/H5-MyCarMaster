package softeer.bemycarmaster.api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetModelsResponse {

	@Schema(description = "모델 식별자", example = "1")
	private Integer id;

	@Schema(description = "모델명", example = "palisade")
	private String name;

	@Schema(description = "모델 이미지", example = "s3 url")
	private String imgUrl;
}
