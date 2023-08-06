package softeer.bemycarmaster.api.domain.trim.usecase;

import softeer.bemycarmaster.api.domain.trim.dto.response.GetTrimsResponse;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
public class GetTrimsUseCase {

	public GetTrimsResponse execute(Long modelId) {

		return new GetTrimsResponse();
	}
}
