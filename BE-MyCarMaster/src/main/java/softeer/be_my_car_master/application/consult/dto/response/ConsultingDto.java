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
public class ConsultingDto {

	@Schema(description = "견적서 url", example = "https://my-car-master.shop/estimates/uuid")
	private String estimateUrl;

	private ClientResponseDto client;

	public static ConsultingDto from(Consulting consulting) {
		final String urlPrefix = "https://my-car-master.shop/estimates/";
		return ConsultingDto.builder()
			.estimateUrl(urlPrefix + consulting.getUuid())
			.client(ClientResponseDto.from(consulting))
			.build();
	}
}
