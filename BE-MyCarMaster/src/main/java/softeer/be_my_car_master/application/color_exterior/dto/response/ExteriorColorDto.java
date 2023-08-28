package softeer.be_my_car_master.application.color_exterior.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExteriorColorDto {

	@Schema(description = "외장 색상 식별자", example = "1")
	private Long id;

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

	public static ExteriorColorDto from(ExteriorColor exteriorColor) {
		return ExteriorColorDto.builder()
			.id(exteriorColor.getId())
			.name(exteriorColor.getName())
			.price(exteriorColor.getPrice())
			.ratio(exteriorColor.getRatio())
			.colorImgUrl(exteriorColor.getColorImgUrl())
			.coloredImgUrl(exteriorColor.getColoredImgUrl())
			.build();
	}
}

