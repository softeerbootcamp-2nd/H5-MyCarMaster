package softeer.be_my_car_master.api.estimate.dto.response;

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
public class EstimateBodyTypeDto {

	@Schema(description = "바디타입 이름", example = "바디타입 이름")
	private String name;

	@Schema(description = "추가 비용", example = "10000")
	private Integer price;

	public static EstimateBodyTypeDto from(BodyType bodyType) {
		return EstimateBodyTypeDto.builder()
			.name(bodyType.getName())
			.price(bodyType.getPrice())
			.build();
	}
}
