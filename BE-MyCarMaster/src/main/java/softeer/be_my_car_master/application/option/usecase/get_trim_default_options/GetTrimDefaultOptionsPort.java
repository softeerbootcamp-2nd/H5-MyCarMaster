package softeer.be_my_car_master.application.option.usecase.get_trim_default_options;

import java.util.List;

import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetTrimDefaultOptionsPort {

	List<Option> findDefaultOptionsByTrim(Long trimId);
}
