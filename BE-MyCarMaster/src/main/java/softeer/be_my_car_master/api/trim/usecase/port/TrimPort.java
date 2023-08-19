package softeer.be_my_car_master.api.trim.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface TrimPort {

	List<Trim> findTrimsByModel(Long modelId);

	List<Long> findTrimIdsByModelId(Long modelId);
}
