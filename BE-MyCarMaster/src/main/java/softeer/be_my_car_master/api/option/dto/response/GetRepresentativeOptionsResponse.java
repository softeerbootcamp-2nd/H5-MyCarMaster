package softeer.be_my_car_master.api.option.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.option.Option;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRepresentativeOptionsResponse {

	private List<RepresentativeOptionDto> representativeOptions;

	public static GetRepresentativeOptionsResponse from(
		List<Option> representativeOptions,
		List<Option> appliedOptions,
		List<Long> trimIdsByModelId,
		List<List<Long>> trimIdsByAdditionalOptionId,
		List<List<Long>> trimIdsByDefaultOptionId
	) {
		List<RepresentativeOptionDto> representativeOptionsDtos = IntStream.range(0, representativeOptions.size())
			.mapToObj(i -> RepresentativeOptionDto.from(
				representativeOptions.get(i),
				appliedOptions.get(i),
				trimIdsByModelId,
				trimIdsByAdditionalOptionId.get(i),
				trimIdsByDefaultOptionId.get(i)
			))
			.collect(Collectors.toList());
		return new GetRepresentativeOptionsResponse(representativeOptionsDtos);
	}
}
