package softeer.bemycarmaster.api.domain.engine.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEnginesResponse {

	private List<Engine> engines;
}
