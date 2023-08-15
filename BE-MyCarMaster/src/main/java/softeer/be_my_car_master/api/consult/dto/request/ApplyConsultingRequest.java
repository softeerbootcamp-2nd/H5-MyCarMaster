package softeer.be_my_car_master.api.consult.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

	@Valid
	private ClientDto client;

	public String getClientName() {
		return client.getName();
	}

	public String getClientEmail() {
        return client.getEmail();
    }

	public String getClientPhone () {
        return client.getPhone();
    }
}
