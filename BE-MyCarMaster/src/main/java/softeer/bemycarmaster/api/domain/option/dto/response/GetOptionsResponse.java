package softeer.bemycarmaster.api.domain.option.dto.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import softeer.bemycarmaster.api.domain.option.dto.OptionEnum;

@Getter
@Setter
public class GetOptionsResponse {

	private Map<OptionEnum, List<Option>> options;
}
