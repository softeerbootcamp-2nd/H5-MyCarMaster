package softeer.be_my_car_master.api.engine.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUnselectableOptionsByEngineResponse {

	private List<UnselectableOptionDto> unselectableOptions;
}
