package softeer.be_my_car_master.application.color_interior.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InteriorColorDto {

	@Schema(description = "내장 색상 식별자", example = "1")
	private Long id;

	@Schema(description = "내장 색상명", example = "퀄팅 천연(블랙)")
	private String name;

	@Schema(description = "내장 색상 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "32")
	private Integer ratio;

	@Schema(description = "내장 색상 이미지", example = "colorImgUrl")
	private String colorImgUrl;

	@Schema(description = "내장 색상이 적용된 차량 내부 이미지", example = "coloredImgUrl")
	private String coloredImgUrl;

	public static InteriorColorDto from(InteriorColor interiorColor) {
		return InteriorColorDto.builder()
			.id(interiorColor.getId())
			.name(interiorColor.getName())
			.price(interiorColor.getPrice())
			.ratio(interiorColor.getRatio())
			.colorImgUrl(interiorColor.getColorImgUrl())
			.coloredImgUrl(interiorColor.getColoredImgUrl())
			.build();
	}
}
