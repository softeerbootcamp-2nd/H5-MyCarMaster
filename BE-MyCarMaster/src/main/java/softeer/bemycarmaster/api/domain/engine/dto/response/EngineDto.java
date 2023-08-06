package softeer.bemycarmaster.api.domain.engine.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EngineDto {

	@Schema(description = "엔진 식별자", example = "1")
	private Integer id;

	@Schema(description = "엔진명", example = "가솔린 3.8")
	private String name;

	@Schema(description = "엔진 설명", example = "가솔린 3.8 Description")
	private String description;

	@Schema(description = "엔진 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "엔진 이미지", example = "s3 url")
	private String imgUrl;

	@Schema(description = "최고 출력", example = "295")
	private Integer power;

	@Schema(description = "최대 토크", example = "36.2")
	private Double toque;

	@Schema(description = "최소 연비", example = "8.0")
	private Double fuelMin;

	@Schema(description = "최대 연비", example = "9.2")
	private Double fuelMax;
}
