package softeer.bemycarmaster.api.domain.color.exterior.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetExteriorColorsResponse {

	private List<ExteriorColorDto> colors;
}
