package softeer.be_my_car_master.api.engine.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.engine.Engine;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEnginesResponse {

	private List<EngineDto> engines;

	public static GetEnginesResponse from(List<Engine> selectableEngines) {
		List<EngineDto> engineDtos = selectableEngines.stream()
			.map(EngineDto::from)
			.collect(Collectors.toList());
		return new GetEnginesResponse(engineDtos);
	}
}
