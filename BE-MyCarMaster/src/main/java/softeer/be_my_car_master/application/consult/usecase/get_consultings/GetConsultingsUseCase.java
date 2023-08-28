package softeer.be_my_car_master.application.consult.usecase.get_consultings;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.application.consult.dto.response.GetConsultingsResponse;
import softeer.be_my_car_master.application.consult.exception.CarMasterNotFoundException;
import softeer.be_my_car_master.domain.consulting.Consulting;
import softeer.be_my_car_master.global.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class GetConsultingsUseCase {

	private final GetConsultingsPort port;

	public GetConsultingsResponse execute(String email, String phone) {
		Long carMasterId = port.findCarMasterIdByForm(email, phone)
			.orElseThrow(() -> CarMasterNotFoundException.EXCEPTION);
		List<Consulting> consultings = port.findConsultingsByCarMaster(carMasterId);
		return GetConsultingsResponse.from(consultings);
	}
}
