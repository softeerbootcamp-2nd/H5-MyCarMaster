package softeer.be_my_car_master.application.trim.usecase.get_trims;

import java.util.List;

import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetTrimsPort {

	List<Trim> findTrimsByModel(Long modelId);
}
