package softeer.bemycarmaster.api.domain.bodytype.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBodytypesResponse {

	private List<Bodytype> bodytypes;
}
