package softeer.be_my_car_master.api.trim.usecase.get_trims;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimsResponse;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetTrimsUseCase {

	private final GetTrimsPort getTrimsPort;

	public GetTrimsResponse execute(Long modelId) {
		List<Trim> trims = getTrimsPort.findTrimsByModel(modelId);
		return GetTrimsResponse.from(trims);
	}
}
