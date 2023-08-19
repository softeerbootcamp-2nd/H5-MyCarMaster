package softeer.be_my_car_master.api.trim.usecase;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.api.trim.dto.response.GetTrimDefaultOptionsResponse;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetTrimDefaultOptionsUseCase {

	private final OptionPort optionPort;

	public GetTrimDefaultOptionsResponse execute(Long trimId) {
		List<Option> defaultOptions = optionPort.findDefaultOptionsByTrim(trimId);
		return GetTrimDefaultOptionsResponse.from(defaultOptions);
	}
}
