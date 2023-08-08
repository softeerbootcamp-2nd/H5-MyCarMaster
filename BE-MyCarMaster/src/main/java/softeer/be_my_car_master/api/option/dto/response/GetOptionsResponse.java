package softeer.be_my_car_master.api.option.dto.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOptionsResponse {

	private List<String> exclusiveTags;

	private Map<OptionEnum, List<Option>> options;
}
