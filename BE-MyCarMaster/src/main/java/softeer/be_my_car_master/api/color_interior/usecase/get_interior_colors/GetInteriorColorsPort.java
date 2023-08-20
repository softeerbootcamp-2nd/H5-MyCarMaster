package softeer.be_my_car_master.api.color_interior.usecase.get_interior_colors;

import java.util.List;

import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface GetInteriorColorsPort {

	List<InteriorColor> findInteriorColorsByTrim(Long trimId);

	List<Long> findUnselectableInteriorColorIdsByExteriorColor(Long exteriorColorId);
}
