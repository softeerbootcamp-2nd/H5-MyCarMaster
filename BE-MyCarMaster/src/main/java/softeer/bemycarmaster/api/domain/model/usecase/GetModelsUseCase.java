package softeer.bemycarmaster.api.domain.model.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.model.domain.Model;
import softeer.bemycarmaster.api.domain.model.dto.response.GetModelsResponse;
import softeer.bemycarmaster.api.domain.model.usecase.port.ModelPort;
import softeer.bemycarmaster.api.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetModelsUseCase {

	private final ModelPort modelPort;

	public GetModelsResponse execute() {
		List<Model> models = modelPort.findModels();
		return GetModelsResponse.from(models);
	}
}
