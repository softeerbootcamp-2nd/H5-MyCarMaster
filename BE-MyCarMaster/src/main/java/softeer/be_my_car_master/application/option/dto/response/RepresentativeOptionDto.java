package softeer.be_my_car_master.application.option.dto.response;

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
public class RepresentativeOptionDto {

	@Schema(description = "옵션 식별자", example = "1")
	private Long id;

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 이미지", example = "null")
	private String imgUrl;

	@Schema(description = "옵션 요약", example = "null")
	private String summary;

	@Schema(description = "옵션 설명", example = "null")
	private String description;

	private List<SubOptionDto> subOptions;

	private FilterDto filter;

	private AppliedOptionDto appliedOption;

	public static RepresentativeOptionDto from(
		Option option,
		Option appliedOption,
		List<Long> trimIds,
		List<Long> additionalTrims,
		List<Long> defaultTrims
	) {
		List<SubOptionDto> subOptionDtos = null;
		if (option.hasSubOption()) {
			subOptionDtos = option.getSubOptions().stream()
				.map(SubOptionDto::from)
				.collect(Collectors.toList());
		}

		return RepresentativeOptionDto.builder()
			.id(option.getId())
			.name(option.getName())
			.imgUrl(option.getImgUrl())
			.summary(option.getSummary())
			.description(option.getDescription())
			.subOptions(subOptionDtos)
			.filter(FilterDto.from(trimIds, additionalTrims, defaultTrims))
			.appliedOption(AppliedOptionDto.from(appliedOption))
			.build();
	}
}
