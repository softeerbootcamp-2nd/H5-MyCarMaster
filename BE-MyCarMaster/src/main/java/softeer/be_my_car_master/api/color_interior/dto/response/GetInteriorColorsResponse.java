package softeer.be_my_car_master.api.color_interior.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInteriorColorsResponse {

	private List<InteriorColorDto> colors;
}
