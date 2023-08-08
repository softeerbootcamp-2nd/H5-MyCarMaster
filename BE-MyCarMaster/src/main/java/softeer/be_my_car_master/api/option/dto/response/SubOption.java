package softeer.be_my_car_master.api.option.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubOption {

	@Schema(description = "하위 옵션명", example = "후방 주차 감지 어쩌구")
	private String name;

	@Schema(description = "하위 옵션 이미지", example = "S3 Url")
	private String imgUrl;

	@Schema(description = "하위 옵션 설명", example = "주차시 어쩌구 저쩌구...")
	private String description;
}
