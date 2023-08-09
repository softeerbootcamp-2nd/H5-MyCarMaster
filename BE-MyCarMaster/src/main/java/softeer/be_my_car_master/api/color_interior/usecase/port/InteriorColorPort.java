package softeer.be_my_car_master.api.color_interior.usecase.port;

import java.util.List;

import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface InteriorColorPort {

	List<InteriorColor> findSelectableInteriorColorsByTrimId(Long trimId);

	List<Long> findUnselectableInteriorColorIdsByExteriorColorId(Long exteriorColorId);
}
