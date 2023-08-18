package softeer.be_my_car_master.api.estimate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.engine.Engine;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleEngineDto {

	@Schema(description = "엔진 이름", example = "엔진 이름")
	private String name;

	@Schema(description = "추가 비용", example = "10000")
	private Integer price;

	public static SimpleEngineDto from(Engine engine) {
		return SimpleEngineDto.builder()
			.name(engine.getName())
			.price(engine.getPrice())
			.build();
	}
}
