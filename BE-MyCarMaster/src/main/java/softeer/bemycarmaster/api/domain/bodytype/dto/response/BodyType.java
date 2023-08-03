package softeer.bemycarmaster.api.domain.bodytype.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyType {

	@Schema(description = "바디타입 식별자", example = "1")
	private Integer id;

	@Schema(description = "바디타입명", example = "7인승")
	private String name;

	@Schema(description = "바디타입 설명", example = "7인승 Description")
	private String description;

	@Schema(description = "바디타입 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "바디타입 이미지", example = "s3 url")
	private String imgUrl;
}
