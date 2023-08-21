package softeer.be_my_car_master.application.model.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.model.Model;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetModelsResponse {

	private List<ModelDto> models;

	public static GetModelsResponse from(List<Model> models) {
		List<ModelDto> modelDtos = models.stream()
			.map(ModelDto::from)
			.collect(Collectors.toList());
		return new GetModelsResponse(modelDtos);
	}
}
