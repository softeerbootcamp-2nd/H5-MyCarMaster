package softeer.be_my_car_master.api.estimate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEstimateResponse {

	private Long estimateId;

	public static CreateEstimateResponse from(Long id) {
		return new CreateEstimateResponse(id);
	}
}
