package softeer.be_my_car_master.api.option.dto.response;

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
public class AppliedOptionDto {

	@Schema(description = "옵션 식별자", example = "1")
	private Long id;

	@Schema(description = "옵션명", example = "주차보조 시스템||")
	private String name;

	@Schema(description = "옵션 추가 비용", example = "1280000")
	private Integer price;

	public static AppliedOptionDto from(Option option) {
		return AppliedOptionDto.builder()
			.id(option.getId())
			.name(option.getName())
			.price(option.getPrice())
			.build();
	}
}
