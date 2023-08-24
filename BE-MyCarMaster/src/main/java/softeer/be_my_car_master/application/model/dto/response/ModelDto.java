package softeer.be_my_car_master.application.model.dto.response;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.model.Model;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelDto {

	@Schema(description = "모델 식별자", example = "1")
	private Long id;

	@Schema(description = "모델명", example = "palisade")
	private String name;

	@Schema(description = "모델 이미지", example = "s3 url")
	private String imgUrl;

	@Schema(description = "최소 가격", example = "100000")
	private Integer price;

	@Schema(description = "타입", example = "SUV")
	private String type;

	@Schema(description = "신 차 여부", example = "true")
	private Boolean isNew;

	public static ModelDto from(Model model) {
		Boolean isNew = isNewModel(model);
		return ModelDto.builder()
			.id(model.getId())
			.name(model.getName())
			.imgUrl(model.getImgUrl())
			.price(model.getPrice())
			.type(model.getType())
			.isNew(isNew)
			.build();
	}

	private static Boolean isNewModel(Model model) {
		LocalDateTime createdAt = model.getCreatedAt();
		LocalDateTime now = LocalDateTime.now();
		long daysBetween = ChronoUnit.DAYS.between(createdAt, now);
		return daysBetween < 30;
	}
}
