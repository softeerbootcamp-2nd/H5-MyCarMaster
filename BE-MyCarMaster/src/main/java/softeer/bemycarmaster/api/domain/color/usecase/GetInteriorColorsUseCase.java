package softeer.bemycarmaster.api.domain.color.usecase;

import softeer.bemycarmaster.api.domain.color.dto.response.GetInteriorColorsResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetInteriorColorsUseCase {

	public GetInteriorColorsResponse execute(Integer modelId, Integer trimId) {
		return new GetInteriorColorsResponse();
	}
}
