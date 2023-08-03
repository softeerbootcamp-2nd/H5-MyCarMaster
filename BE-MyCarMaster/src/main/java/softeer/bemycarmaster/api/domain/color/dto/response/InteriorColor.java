package softeer.bemycarmaster.api.domain.color.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InteriorColor {

	@Schema(description = "내장 색상 식별자", example = "1")
	private Integer id;

	@Schema(description = "내장 색상명", example = "퀄팅 천연(블랙)")
	private String name;

	@Schema(description = "내장 색상 추가 가격", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "32")
	private Integer ratio;

	@Schema(description = "내장 색상 이미지", example = "colorImgUrl")
	private String colorImgUrl;

	@Schema(description = "내장 색상이 적용된 차량 내부 이미지", example = "coloredImgUrl")
	private String coloredImgUrl;
}
