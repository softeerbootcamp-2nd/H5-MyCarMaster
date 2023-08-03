package softeer.bemycarmaster.api.domain.color.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InteriorColor {

	@Schema(description = "색상 식별자", example = "1")
	private Integer id;

	@Schema(description = "색상명", example = "palisade")
	private String name;

	@Schema(description = "색상 가격", example = "1000")
	private Integer price;

	@Schema(description = "색상 비율", example = "32")
	private Integer ratio;

	@Schema(description = "색상 이미지", example = "colorImgUrl")
	private String colorImgUrl;

	@Schema(description = "색상 이미지", example = "coloredImgUrl")
	private String coloredImgUrl;
}
