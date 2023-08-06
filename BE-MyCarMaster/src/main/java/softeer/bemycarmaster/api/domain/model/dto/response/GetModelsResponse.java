package softeer.bemycarmaster.api.domain.model.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetModelsResponse {

	private List<ModelDto> models;
}
