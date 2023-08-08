package softeer.be_my_car_master.api.trim.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.trim.Trim;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TrimDto {

	@Schema(description = "트림 식별자", example = "1")
	private Long id;

	@Schema(description = "트림명", example = "Le Blanc")
	private String name;

	@Schema(description = "트림 설명", example = "Le Blanc Trim Description")
	private String description;

	@Schema(description = "트림 기본 가격", example = "47720000")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "트림 이미지", example = "s3 url")
	private String imgUrl;

	public static TrimDto from(Trim trim) {
		return TrimDto.builder()
			.id(trim.getId())
			.name(trim.getName())
			.description(trim.getDescription())
			.price(trim.getPrice())
			.ratio(trim.getRatio())
			.imgUrl(trim.getImgUrl())
			.build();
	}
}
