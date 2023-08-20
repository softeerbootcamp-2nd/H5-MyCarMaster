package softeer.be_my_car_master.api.estimate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimateInteriorColorDto {

	@Schema(description = "내장색상명", example = "그라파이트 그레이 메탈릭")
	private String name;

	@Schema(description = "내장색상 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "내장색상 이미지", example = "colorImgUrl")
	private String colorImgUrl;

	public static EstimateInteriorColorDto from(InteriorColor interiorColor) {
		return EstimateInteriorColorDto.builder()
			.name(interiorColor.getName())
			.price(interiorColor.getPrice())
			.colorImgUrl(interiorColor.getColorImgUrl())
			.build();
	}
}
