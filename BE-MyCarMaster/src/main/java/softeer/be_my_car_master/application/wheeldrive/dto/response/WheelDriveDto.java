package softeer.be_my_car_master.application.wheeldrive.dto.response;

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
public class WheelDriveDto {

	@Schema(description = "구동방식 식별자", example = "1")
	private Long id;

	@Schema(description = "구동방식명", example = "2WD")
	private String name;

	@Schema(description = "구동방식 설명", example = "2WD Description")
	private String description;

	@Schema(description = "구동방식 추가 비용", example = "0")
	private Integer price;

	@Schema(description = "판매율", example = "22")
	private Integer ratio;

	@Schema(description = "구동방식 이미지", example = "s3 url")
	private String imgUrl;

	public static WheelDriveDto from(WheelDrive wheelDrive) {
		return WheelDriveDto.builder()
			.id(wheelDrive.getId())
			.name(wheelDrive.getName())
			.description(wheelDrive.getDescription())
			.price(wheelDrive.getPrice())
			.ratio(wheelDrive.getRatio())
			.imgUrl(wheelDrive.getImgUrl())
			.build();
	}
}
