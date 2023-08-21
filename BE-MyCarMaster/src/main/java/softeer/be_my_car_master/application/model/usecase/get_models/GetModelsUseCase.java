package softeer.be_my_car_master.application.model.usecase.get_models;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.model.dto.response.GetModelsResponse;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetModelsUseCase {

	private final GetModelsPort getModelsPort;

	public GetModelsResponse execute() {
		List<Model> models = getModelsPort.findModels();
		return GetModelsResponse.from(models);
	}
}
