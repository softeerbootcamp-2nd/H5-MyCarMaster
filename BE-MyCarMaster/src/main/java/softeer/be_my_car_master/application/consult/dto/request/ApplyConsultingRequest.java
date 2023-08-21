package softeer.be_my_car_master.application.consult.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyConsultingRequest {

	@Schema(description = "견적서 식별자", example = "62dd98f0-bd8e-11ed-93ab-325096b39f47")
	@NotNull(message = "estimateId는 Null 일 수 없습니다.")
	@Pattern(
		regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
		message = "잘못된 estimateId 형식입니다."
	)
	private String estimateId;

	@Schema(description = "카마스터 식별자", example = "1")
	@NotNull(message = "carMasterId는 Null일 수 없습니다.")
	@Min(value = 1, message = "carMasterId는 1 이상의 값입니다.")
	private Long carMasterId;

	@Valid
	private ClientDto client;

	@Hidden
	public String getClientName() {
		return client.getName();
	}

	@Hidden
	public String getClientEmail() {
		return client.getEmail();
	}

	@Hidden
	public String getClientPhone() {
		return client.getPhone();
	}
}
