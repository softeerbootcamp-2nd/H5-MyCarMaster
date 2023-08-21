package softeer.be_my_car_master.application.estimate.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEstimateResponse {

	private UUID estimateUuid;

	public static CreateEstimateResponse from(UUID uuid) {
		return new CreateEstimateResponse(uuid);
	}
}
