package softeer.be_my_car_master.api.estimate.dto.response;

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
public class EstimateTrimDto {

	@Schema(description = "트림 이름", example = "트림 이름")
	private String name;

	@Schema(description = "트림 기본 비용", example = "40000000")
	private Integer price;

	public static EstimateTrimDto from(Trim trim) {
		return EstimateTrimDto.builder()
			.name(trim.getName())
			.price(trim.getPrice())
			.build();
	}
}
