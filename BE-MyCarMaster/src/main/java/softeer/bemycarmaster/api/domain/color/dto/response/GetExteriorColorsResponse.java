package softeer.bemycarmaster.api.domain.color.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetExteriorColorsResponse {

	private List<ExteriorColor> colors;
}
