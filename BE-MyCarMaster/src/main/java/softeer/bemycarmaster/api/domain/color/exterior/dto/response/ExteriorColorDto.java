package softeer.bemycarmaster.api.domain.color.exterior.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExteriorColorDto {

	@Schema(description = "외장 색상 식별자", example = "1")
	private Integer id;

	@Schema(description = "외장 색상명", example = "그라파이트 그레이 메탈릭")
	private String name;

	@Schema(description = "외장 색상 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "32")
	private Integer ratio;

	@Schema(description = "외장 색상 이미지", example = "colorImgUrl")
	private String colorImgUrl;

	@Schema(description = "외장 색상이 적용된 차량 이미지", example = "coloredImgUrl")
	private String coloredImgUrl;
}
