package softeer.be_my_car_master.api.engine.usecase.get_engines;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.engine.dto.response.GetEnginesResponse;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetEnginesUseCase {

	private final GetEnginesPort getEnginesPort;

	public GetEnginesResponse execute(Long trimId) {
		List<Engine> engines = getEnginesPort.findEnginesByTrim(trimId);
		return GetEnginesResponse.from(engines);
	}
}
