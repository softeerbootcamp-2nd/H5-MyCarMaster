package softeer.be_my_car_master.application.option.usecase.get_trim_default_options;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetTrimDefaultOptionsUseCase {

	private final GetTrimDefaultOptionsPort port;

	public GetTrimDefaultOptionsResponse execute(Long trimId) {
		List<Option> defaultOptions = port.findDefaultOptionsByTrim(trimId);
		return GetTrimDefaultOptionsResponse.from(defaultOptions);
	}
}
