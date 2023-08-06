package softeer.bemycarmaster.api.domain.color.interior.usecase;

import softeer.bemycarmaster.api.domain.color.interior.dto.response.GetInteriorColorsResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetInteriorColorsUseCase {

	public GetInteriorColorsResponse execute(Integer trimId, Integer exteriorColorId) {
		return new GetInteriorColorsResponse();
	}
}
