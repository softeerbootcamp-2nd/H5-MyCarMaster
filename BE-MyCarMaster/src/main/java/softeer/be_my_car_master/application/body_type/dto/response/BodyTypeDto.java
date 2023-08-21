package softeer.be_my_car_master.application.body_type.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.body_type.BodyType;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BodyTypeDto {

	@Schema(description = "바디타입 식별자", example = "1")
	private Long id;

	@Schema(description = "바디타입명", example = "7인승")
	private String name;

	@Schema(description = "바디타입 설명", example = "7인승 Description")
	private String description;

	@Schema(description = "바디타입 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "바디타입 이미지", example = "s3 url")
	private String imgUrl;

	public static BodyTypeDto from(BodyType bodyType) {
		return BodyTypeDto.builder()
			.id(bodyType.getId())
			.name(bodyType.getName())
			.description(bodyType.getDescription())
			.price(bodyType.getPrice())
			.ratio(bodyType.getRatio())
			.imgUrl(bodyType.getImgUrl())
			.build();
	}
}
