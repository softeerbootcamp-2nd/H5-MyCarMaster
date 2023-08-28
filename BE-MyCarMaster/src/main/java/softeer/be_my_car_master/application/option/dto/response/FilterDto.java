package softeer.be_my_car_master.application.option.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FilterDto {

	@Schema(description = "해당 기능을 추가할 수 없는 trimId 목록", example = "1")
	private List<Long> unavailableTrimIds;

	@Schema(description = "해당 기능을 추가 옵션으로 갖는 trimId 목록", example = "2, 3")
	private List<Long> additionalTrimIds;

	@Schema(description = "해당 기능을 기본 옵션으로 갖는 trimId 목록", example = "4")
	private List<Long> defaultTrimIds;

	public static FilterDto from(List<Long> trimIds, List<Long> additionalTrimIds, List<Long> defaultTrimIds) {
		List<Long> unavailableTrimIds = trimIds.stream()
			.filter(trimId -> isUnavailable(trimId, additionalTrimIds, defaultTrimIds))
			.collect(Collectors.toList());
		return new FilterDto(unavailableTrimIds, additionalTrimIds, defaultTrimIds);
	}

	private static boolean isUnavailable(Long trimId, List<Long> additionalTrimIds, List<Long> defaultTrimIds) {
		return !additionalTrimIds.contains(trimId) && !defaultTrimIds.contains(trimId);
	}
}
