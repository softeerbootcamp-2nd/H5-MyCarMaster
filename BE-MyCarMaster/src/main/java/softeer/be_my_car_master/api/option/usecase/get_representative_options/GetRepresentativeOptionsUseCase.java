package softeer.be_my_car_master.api.option.usecase.get_representative_options;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.dto.response.GetRepresentativeOptionsResponse;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetRepresentativeOptionsUseCase {

	private final GetRepresentativeOptionsPort port;

	public GetRepresentativeOptionsResponse execute(Long modelId) {
		List<Option> representativeOptions = port.findRepresentativeOptionsByModel(modelId);
		List<Long> representativeOptionIds = representativeOptions.stream()
			.map(Option::getId)
			.collect(Collectors.toList());
		List<Option> appliedOptions =
			port.findAppliedOptionsByModelAndOptions(modelId, representativeOptionIds);

		List<Long> trimIdsByModelId = port.findTrimIdsByModel(modelId);

		List<List<Long>> trimIdsByAdditionalOptionId = appliedOptions.stream()
			.map(Option::getId)
			.map(port::findAdditionalTrimIdsByOption)
			.collect(Collectors.toList());

		List<List<Long>> trimIdsByDefaultOptionId = representativeOptions.stream()
			.map(Option::getId)
			.map(port::findDefaultTrimIdsByOption)
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
