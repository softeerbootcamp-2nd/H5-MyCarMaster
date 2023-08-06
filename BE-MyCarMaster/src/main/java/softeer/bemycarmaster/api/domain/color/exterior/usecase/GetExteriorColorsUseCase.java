package softeer.bemycarmaster.api.domain.color.exterior.usecase;

import softeer.bemycarmaster.api.domain.color.exterior.dto.response.GetExteriorColorsResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetExteriorColorsUseCase {

	public GetExteriorColorsResponse execute(Integer modelId, Integer trimId) {
		return new GetExteriorColorsResponse();
	}
}
