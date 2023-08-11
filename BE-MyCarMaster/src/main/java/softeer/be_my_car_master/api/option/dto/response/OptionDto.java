package softeer.be_my_car_master.api.option.dto.response;

import java.util.List;
import java.util.stream.Collectors;

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
public class OptionDto {

	@Schema(description = "옵션 식별자", example = "1")
	private Long id;

	@Schema(description = "카테고리", example = "SAFE")
	private String category;

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 기본 비용", example = "1280000")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "옵션 이미지", example = "null")
	private String imgUrl;

	@Schema(description = "옵션 요약", example = "null")
	private String summary;

	@Schema(description = "옵션 설명", example = "null")
	private String description;

	@Schema(description = "태그", example = "null")
	private String tag;

	private List<SubOptionDto> subOptions;

	public static OptionDto from(Option option) {
		List<SubOptionDto> subOptionDtos = null;
		if (option.hasSubOption()) {
			subOptionDtos = option.getSubOptions().stream()
				.map(SubOptionDto::from)
				.collect(Collectors.toList());
		}

		return OptionDto.builder()
			.id(option.getId())
			.category(option.getCategory())
			.name(option.getName())
			.price(option.getPrice())
			.ratio(option.getRatio())
			.imgUrl(option.getImgUrl())
			.summary(option.getSummary())
			.description(option.getDescription())
			.tag(option.getTagName())
			.subOptions(subOptionDtos)
			.build();
	}
}
