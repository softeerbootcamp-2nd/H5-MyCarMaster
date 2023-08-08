package softeer.be_my_car_master.api.body_type.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softeer.be_my_car_master.domain.body_type.BodyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetBodyTypesResponse {

	private List<BodyTypeDto> bodyTypes;

	public static GetBodyTypesResponse from(List<BodyType> bodyTypes) {
		List<BodyTypeDto> bodyTypeDtos = bodyTypes.stream()
			.map(BodyTypeDto::from)
			.collect(Collectors.toList());
		return new GetBodyTypesResponse(bodyTypeDtos);
	}
}
