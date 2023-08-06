package softeer.bemycarmaster.api.domain.trim.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrimDto {

	@Schema(description = "트림 식별자", example = "1")
	private Long id;

	@Schema(description = "트림명", example = "Le Blanc")
	private String name;

	@Schema(description = "트림 설명", example = "Le Blanc Trim Description")
	private String description;

	@Schema(description = "트림 기본 가격", example = "47720000")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "트림 이미지", example = "s3 url")
	private String imgUrl;
}
