package softeer.be_my_car_master.api.model.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.model.dto.response.GetModelsResponse;
import softeer.be_my_car_master.api.model.usecase.port.ModelPort;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetModelsUseCase {

	private final ModelPort modelPort;

	public GetModelsResponse execute() {
		List<Model> models = modelPort.findModels();
		return GetModelsResponse.from(models);
	}
}
