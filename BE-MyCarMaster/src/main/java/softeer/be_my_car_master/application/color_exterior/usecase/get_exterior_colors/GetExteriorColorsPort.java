package softeer.be_my_car_master.application.color_exterior.usecase.get_exterior_colors;

import java.util.List;

import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetExteriorColorsPort {

	List<ExteriorColor> findExteriorColorsByTrim(Long trimId);
}
