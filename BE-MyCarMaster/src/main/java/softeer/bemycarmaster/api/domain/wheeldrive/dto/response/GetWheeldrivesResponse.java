package softeer.bemycarmaster.api.domain.wheeldrive.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetWheeldrivesResponse {

	private List<Wheeldrive> wheeldrives;
}
