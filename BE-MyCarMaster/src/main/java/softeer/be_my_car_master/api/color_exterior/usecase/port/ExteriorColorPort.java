package softeer.be_my_car_master.api.color_exterior.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface ExteriorColorPort {

	List<ExteriorColor> findSelectableExteriorColorsByTrimId(Long trimId);
}
