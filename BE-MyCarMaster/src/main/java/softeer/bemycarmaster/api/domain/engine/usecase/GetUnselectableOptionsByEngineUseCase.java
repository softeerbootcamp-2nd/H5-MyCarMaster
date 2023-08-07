package softeer.bemycarmaster.api.domain.engine.usecase;

import java.util.List;

import softeer.bemycarmaster.api.domain.engine.dto.response.GetUnselectableOptionsByEngineResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetUnselectableOptionsByEngineUseCase {

	public GetUnselectableOptionsByEngineResponse execute(Long engineId, Long trimId, List<Long> optionIds) {
		return new GetUnselectableOptionsByEngineResponse();
	}
}
