package softeer.be_my_car_master.application.estimate.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.estimate.Estimate;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GetEstimateResponse {

	private EstimateTrimDto trim;
	private EstimateEngineDto engine;
	private EstimateWheelDriveDto wheelDrive;
	private EstimateBodyTypeDto bodyType;
	private EstimateExteriorColorDto exteriorColor;
	private EstimateInteriorColorDto interiorColor;
	private List<EstimateOptionResponseDto> selectOptions;
	private List<EstimateOptionResponseDto> considerOptions;

	public static GetEstimateResponse from(Estimate estimate) {
		List<EstimateOptionResponseDto> selectOptionDtos = estimate.getSelectOptions().stream()
			.map(EstimateOptionResponseDto::from)
			.collect(toList());

		List<EstimateOptionResponseDto> considerOptionDtos = estimate.getConsiderOptions().stream()
			.map(EstimateOptionResponseDto::from)
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
