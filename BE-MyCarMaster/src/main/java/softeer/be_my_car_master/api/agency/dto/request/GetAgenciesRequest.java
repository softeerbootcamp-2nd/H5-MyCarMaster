package softeer.be_my_car_master.api.agency.dto.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAgenciesRequest {

	@Schema(description = "구", example = "성동구")
	@NotEmpty(message = "gu는 빈 값일 수 없습니다.")
	private String gu;
}
