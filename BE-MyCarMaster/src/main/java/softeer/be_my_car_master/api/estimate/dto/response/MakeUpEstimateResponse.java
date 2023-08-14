package softeer.be_my_car_master.api.estimate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MakeUpEstimateResponse {

	private Long estimateId;

	public static MakeUpEstimateResponse from(Long id) {
		return new MakeUpEstimateResponse(id);
	}
}
