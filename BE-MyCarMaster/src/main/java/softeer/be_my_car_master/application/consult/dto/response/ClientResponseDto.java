package softeer.be_my_car_master.application.consult.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import softeer.be_my_car_master.domain.consulting.Consulting;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientResponseDto {

	@Schema(description = "고객 성함", example = "Hyundai")
	private String name;

	@Schema(description = "고객 이메일", example = "Hyundai@example.com")
	private String email;

	@Schema(description = "고객 번호", example = "010-0000-0000")
	private String phone;

	public static ClientResponseDto from(Consulting consulting) {
		return ClientResponseDto.builder()
			.name(consulting.getClientName())
			.email(consulting.getClientEmail())
			.phone(consulting.getClientPhone())
			.build();
	}
}
