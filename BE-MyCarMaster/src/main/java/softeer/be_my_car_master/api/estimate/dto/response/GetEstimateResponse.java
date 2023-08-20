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

	private EstimateTrimDto trim;
	private EstimateEngineDto engine;
	private EstimateWheelDriveDto wheelDrive;
	private EstimateBodyTypeDto bodyType;
	private EstimateExteriorColorDto exteriorColor;
	private EstimateInteriorColorDto interiorColor;
	private List<EstimateOptionDto> selectOptions;
	private List<EstimateOptionDto> considerOptions;

	public static GetEstimateResponse from(Estimate estimate) {
		List<EstimateOptionDto> selectOptionDtos = estimate.getSelectOptions().stream()
			.map(EstimateOptionDto::from)
			.collect(toList());

		List<EstimateOptionDto> considerOptionDtos = estimate.getConsiderOptions().stream()
			.map(EstimateOptionDto::from)
			.collect(toList());

		return new GetEstimateResponse(
			EstimateTrimDto.from(estimate.getTrim()),
			EstimateEngineDto.from(estimate.getEngine()),
			EstimateWheelDriveDto.from(estimate.getWheelDrive()),
			EstimateBodyTypeDto.from(estimate.getBodyType()),
			EstimateExteriorColorDto.from(estimate.getExteriorColor()),
			EstimateInteriorColorDto.from(estimate.getInteriorColor()),
			selectOptionDtos,
			considerOptionDtos
		);
	}
}
