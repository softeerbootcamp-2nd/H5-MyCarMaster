package softeer.be_my_car_master.api.estimate.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.estimate.Estimate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEstimateResponse {

	private SimpleTrimDto trim;
	private SimpleEngineDto engine;
	private SimpleWheelDriveDto wheelDrive;
	private SimpleBodyTypeDto bodyType;
	private SimpleExteriorColorDto exteriorColor;
	private SimpleInteriorColorDto interiorColor;
	private List<SimpleOptionDto> additionalOptions;
	private List<SimpleOptionDto> considerOptions;

	public static GetEstimateResponse from(Estimate estimate) {
		List<SimpleOptionDto> additionalOptionDtos = estimate.getAdditionalOptions().stream()
			.map(SimpleOptionDto::from)
			.collect(toList());
		List<SimpleOptionDto> considerOptionDtos = estimate.getConsiderOptions().stream()
			.map(SimpleOptionDto::from)
			.collect(toList());
		return new GetEstimateResponse(
			SimpleTrimDto.from(estimate.getTrim()),
			SimpleEngineDto.from(estimate.getEngine()),
			SimpleWheelDriveDto.from(estimate.getWheelDrive()),
			SimpleBodyTypeDto.from(estimate.getBodyType()),
			SimpleExteriorColorDto.from(estimate.getExteriorColor()),
			SimpleInteriorColorDto.from(estimate.getInteriorColor()),
			additionalOptionDtos,
			considerOptionDtos
		);
	}
}
