package softeer.be_my_car_master.api.engine.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.engine.dto.response.GetEnginesResponse;
import softeer.be_my_car_master.api.engine.usecase.port.EnginePort;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetEnginesUseCase {

	private final EnginePort enginePort;

	public GetEnginesResponse execute(Long trimId) {
		List<Engine> selectableEngines = enginePort.findSelectableEnginesByTrimId(trimId);
		return GetEnginesResponse.from(selectableEngines);
	}
}
