package softeer.be_my_car_master.api.option.usecase.port;

import java.util.List;

import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface TagPort {

	List<String> findSingleSelectableTags();
}
