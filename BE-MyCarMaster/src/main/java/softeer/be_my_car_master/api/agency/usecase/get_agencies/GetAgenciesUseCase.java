package softeer.be_my_car_master.api.agency.usecase.get_agencies;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.agency.dto.response.GetAgenciesResponse;
import softeer.be_my_car_master.domain.agency.Agency;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetAgenciesUseCase {

	private final GetAgenciesPort port;

	public GetAgenciesResponse execute(String gu) {
		List<Agency> agencies = port.findAgenciesInGu(gu);
		return GetAgenciesResponse.from(agencies);
	}
}
