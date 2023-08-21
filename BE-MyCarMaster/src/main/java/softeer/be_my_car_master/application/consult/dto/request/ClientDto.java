package softeer.be_my_car_master.application.consult.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

	@Schema(description = "고객 성함", example = "Hyundai")
	@Size(min = 2, max = 50, message = "name은 최소 2자 ~ 최대 50자입니다.")
	@NotEmpty(message = "name은 빈 값일 수 없습니다.")
	private String name;

	@Schema(description = "고객 이메일", example = "Hyundai@example.com")
	@Size(min = 5, max = 255, message = "이메일은 최대 길이는 255입니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "잘못된 이메일 형식입니다.")
	@NotEmpty(message = "email은 빈 값일 수 없습니다.")
	private String email;

	@Schema(description = "고객 번호", example = "010-0000-0000")
	@Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "잘못된 번호 형식입니다.")
	@NotEmpty(message = "phone은 빈 값일 수 없습니다.")
	private String phone;
}
