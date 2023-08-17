package softeer.be_my_car_master.api.trim.dto.response;

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
public class TrimDefaultOptionDto {

	@Schema(description = "옵션 식별자", example = "1")
	private Long id;

	@Schema(description = "카테고리", example = "안전")
	private String category;

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 이미지", example = "s3 url")
	private String imgUrl;

	@Schema(description = "옵션 설명", example = "null")
	private String description;

	public static TrimDefaultOptionDto from(Option option) {
		return TrimDefaultOptionDto.builder()
			.id(option.getId())
			.category(option.getCategoryValue())
			.name(option.getName())
			.imgUrl(option.getImgUrl())
			.description(option.getDescription())
			.build();
	}
}
