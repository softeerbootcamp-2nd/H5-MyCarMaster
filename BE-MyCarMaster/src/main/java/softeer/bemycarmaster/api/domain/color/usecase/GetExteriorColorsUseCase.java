package softeer.bemycarmaster.api.domain.color.usecase;

import softeer.bemycarmaster.api.domain.color.dto.response.GetExteriorColorsResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetExteriorColorsUseCase {

	public GetExteriorColorsResponse execute(Integer modelId, Integer colorId) {
		return new GetExteriorColorsResponse();
	}
}
