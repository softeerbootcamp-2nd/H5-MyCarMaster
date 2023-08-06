package softeer.bemycarmaster.api.domain.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.bemycarmaster.api.domain.model.domain.Model;

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

	public static ModelDto from(Model model) {
		return ModelDto.builder()
			.id(model.getId())
			.name(model.getName())
			.imgUrl(model.getImgUrl())
			.build();
	}
}
