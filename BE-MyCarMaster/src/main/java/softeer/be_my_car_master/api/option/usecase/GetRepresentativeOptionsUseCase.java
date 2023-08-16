package softeer.be_my_car_master.api.option.usecase;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.api.trim.usecase.port.TrimPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetRepresentativeOptionsUseCase {

	private final OptionPort optionPort;
	private final TrimPort trimPort;

	public GetRepresentativeOptionsResponse execute(Long modelId) {

		List<Option> representativeOptions = optionPort.findRepresentativeOptionsByModelId(modelId);
		List<Long> representativeOptionIds = representativeOptions.stream()
			.map(Option::getId)
			.collect(Collectors.toList());
		List<Option> appliedOptions =
			optionPort.findAppliedOptionsByModelIdAndOptionIds(modelId, representativeOptionIds);

		List<Long> trimIdsByModelId = trimPort.findTrimIdsByModelId(modelId);

		List<List<Long>> trimIdsByAdditionalOptionId = appliedOptions.stream()
			.map(Option::getId)
			.map(optionPort::findAdditionalTrimIdsByOptionId)
			.collect(Collectors.toList());

		List<List<Long>> trimIdsByDefaultOptionId = representativeOptions.stream()
			.map(Option::getId)
			.map(optionPort::findDefaultTrimIdsByOptionId)
			.collect(Collectors.toList());

		return GetRepresentativeOptionsResponse.from(
			representativeOptions,
			appliedOptions,
			trimIdsByModelId,
			trimIdsByAdditionalOptionId,
			trimIdsByDefaultOptionId
		);
	}
}
