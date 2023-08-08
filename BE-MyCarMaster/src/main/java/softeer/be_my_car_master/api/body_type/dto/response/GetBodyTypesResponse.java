package softeer.be_my_car_master.api.body_type.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBodyTypesResponse {

	private List<BodyTypeDto> bodyTypes;
}
