package softeer.bemycarmaster.api.domain.option.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option {

	@Schema(description = "옵션 식별자", example = "1")
	private Integer id;

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 기본 비용", example = "1280000")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "옵션 이미지", example = "null")
	private String imgUrl;

	@Schema(description = "옵션 설명", example = "null")
	private String description;

	private List<PackageOptionDetail> packageOptionDetails;
}
