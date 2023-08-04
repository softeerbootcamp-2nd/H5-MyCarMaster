package softeer.bemycarmaster.api.domain.option.usecase;

import softeer.bemycarmaster.api.domain.option.dto.response.GetOptionsResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetOptionsUseCase {

	public GetOptionsResponse execute(Integer modelId, Integer trimId, Integer engineId) {
		return new GetOptionsResponse();
	}
}
