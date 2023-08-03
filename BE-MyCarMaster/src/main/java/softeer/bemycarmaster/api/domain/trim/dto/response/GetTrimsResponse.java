package softeer.bemycarmaster.api.domain.trim.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTrimsResponse {

	private List<Trim> trims;
}
