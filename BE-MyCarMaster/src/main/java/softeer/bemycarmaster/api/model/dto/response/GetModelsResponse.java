package softeer.bemycarmaster.api.model.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetModelsResponse {

	private List<Model> models;
}
