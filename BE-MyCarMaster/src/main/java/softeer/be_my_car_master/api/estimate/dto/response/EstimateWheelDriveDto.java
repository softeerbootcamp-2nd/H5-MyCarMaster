package softeer.be_my_car_master.api.estimate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimateWheelDriveDto {

	@Schema(description = "구동방식 이름", example = "구동방식 이름")
	private String name;

	@Schema(description = "추가 비용", example = "10000")
	private Integer price;

	public static EstimateWheelDriveDto from(WheelDrive wheelDrive) {
		return EstimateWheelDriveDto.builder()
			.name(wheelDrive.getName())
			.price(wheelDrive.getPrice())
			.build();
	}
}
