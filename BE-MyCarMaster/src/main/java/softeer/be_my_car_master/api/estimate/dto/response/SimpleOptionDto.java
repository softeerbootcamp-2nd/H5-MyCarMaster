package softeer.be_my_car_master.api.estimate.dto.response;

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
public class SimpleOptionDto {

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 기본 비용", example = "1280000")
	private Integer price;

	@Schema(description = "옵션 이미지", example = "null")
	private String imgUrl;

	public static SimpleOptionDto from(Option option) {
		return SimpleOptionDto.builder()
			.name(option.getName())
			.price(option.getPrice())
			.imgUrl(option.getImgUrl())
			.build();
	}
}
