package softeer.bemycarmaster.api.domain.trim.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.trim.domain.Trim;
import softeer.bemycarmaster.api.domain.trim.dto.response.GetTrimsResponse;
import softeer.bemycarmaster.api.domain.trim.usecase.port.TrimPort;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetTrimsUseCase {

	private final TrimPort trimPort;

	public GetTrimsResponse execute(Long modelId) {
		List<Trim> trims = trimPort.findTrims(modelId);
		return GetTrimsResponse.from(trims);
	}
}
