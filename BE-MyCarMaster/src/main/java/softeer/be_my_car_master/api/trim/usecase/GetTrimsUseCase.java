package softeer.be_my_car_master.api.trim.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.api.trim.usecase.port.TrimPort;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetTrimsUseCase {

	private final TrimPort trimPort;

	public GetTrimsResponse execute(Long modelId) {
		List<Trim> trims = trimPort.findTrims(modelId);
		return GetTrimsResponse.from(trims);
	}
}
