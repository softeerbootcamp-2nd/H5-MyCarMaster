package softeer.be_my_car_master.api.color_exterior.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetExteriorColorsResponse {

	private List<ExteriorColorDto> colors;
}
