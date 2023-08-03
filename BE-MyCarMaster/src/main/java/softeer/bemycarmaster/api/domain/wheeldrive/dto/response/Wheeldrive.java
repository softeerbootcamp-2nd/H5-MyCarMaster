package softeer.bemycarmaster.api.domain.wheeldrive.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wheeldrive {

	@Schema(description = "구동방식 식별자", example = "1")
	private Integer id;

	@Schema(description = "구동방식명", example = "2WD")
	private String name;

	@Schema(description = "구동방식 설명", example = "2WD Description")
	private String description;

	@Schema(description = "구동방식 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "구동방식 이미지", example = "s3 url")
	private String imgUrl;
}
