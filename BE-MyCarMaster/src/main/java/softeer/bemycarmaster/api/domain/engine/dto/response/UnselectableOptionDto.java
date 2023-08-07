package softeer.bemycarmaster.api.domain.engine.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnselectableOptionDto {
	@Schema(description = "옵션 식별자", example = "1")
	private Integer id;

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 추가 비용", example = "1280000")
	private Integer price;
}
