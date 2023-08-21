package softeer.be_my_car_master.application.estimate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.option.Option;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimateOptionResponseDto {

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 기본 비용", example = "1280000")
	private Integer price;

	@Schema(description = "옵션 이미지", example = "null")
	private String imgUrl;

	public static EstimateOptionResponseDto from(Option option) {
		return EstimateOptionResponseDto.builder()
			.name(option.getName())
			.price(option.getPrice())
			.imgUrl(option.getImgUrl())
			.build();
	}
}
