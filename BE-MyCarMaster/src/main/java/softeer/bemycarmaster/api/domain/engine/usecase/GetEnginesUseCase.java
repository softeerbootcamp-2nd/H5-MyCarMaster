package softeer.bemycarmaster.api.domain.engine.usecase;

import softeer.bemycarmaster.api.domain.engine.dto.response.GetEnginesResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetEnginesUseCase {

	public GetEnginesResponse execute(Integer trimId) {

		return new GetEnginesResponse();
	}
}
