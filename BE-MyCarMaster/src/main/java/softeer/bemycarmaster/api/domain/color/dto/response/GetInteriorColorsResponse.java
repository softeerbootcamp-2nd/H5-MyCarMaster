package softeer.bemycarmaster.api.domain.color.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInteriorColorsResponse {

	private List<InteriorColor> colors;
}