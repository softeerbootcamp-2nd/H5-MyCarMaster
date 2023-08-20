package softeer.be_my_car_master.api.estimate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimateExteriorColorDto {

	@Schema(description = "외장색상명", example = "그라파이트 그레이 메탈릭")
	private String name;

	@Schema(description = "외장색상 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "외장색상 이미지", example = "colorImgUrl")
	private String colorImgUrl;

	@Schema(description = "외장색상이 적용된 차량 이미지", example = "coloredImgUrl")
	private String coloredImgUrl;

	public static EstimateExteriorColorDto from(ExteriorColor exteriorColor) {
		return EstimateExteriorColorDto.builder()
			.name(exteriorColor.getName())
			.price(exteriorColor.getPrice())
			.colorImgUrl(exteriorColor.getColorImgUrl())
			.coloredImgUrl(exteriorColor.getColoredImgUrl())
			.build();
	}
}
