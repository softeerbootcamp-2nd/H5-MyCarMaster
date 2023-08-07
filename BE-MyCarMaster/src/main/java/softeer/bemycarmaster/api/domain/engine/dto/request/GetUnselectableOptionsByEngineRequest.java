package softeer.bemycarmaster.api.domain.engine.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUnselectableOptionsByEngineRequest {

	@Schema(description = "트림 식별자", example = "1")
	private Long trimId;

	@Schema(description = "기존에 선택된 옵션 식별자 목록", example = "[1, 2, 3]")
	private List<Long> optionsIds;
}
