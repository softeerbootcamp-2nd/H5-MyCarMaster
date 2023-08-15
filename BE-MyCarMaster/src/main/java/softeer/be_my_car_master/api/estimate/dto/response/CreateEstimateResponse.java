package softeer.be_my_car_master.api.estimate.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEstimateResponse {

	private UUID estimateId;

	public static CreateEstimateResponse from(UUID id) {
		return new CreateEstimateResponse(id);
	}
}
