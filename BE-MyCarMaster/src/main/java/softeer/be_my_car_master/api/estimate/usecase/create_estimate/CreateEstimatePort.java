package softeer.be_my_car_master.api.estimate.usecase.create_estimate;

import java.util.List;
import java.util.UUID;

import softeer.be_my_car_master.api.estimate.dto.request.CreateEstimateRequest;
import softeer.be_my_car_master.domain.body_type.BodyType;
import softeer.be_my_car_master.domain.color_exterior.ExteriorColor;
import softeer.be_my_car_master.domain.color_interior.InteriorColor;
import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.domain.model.Model;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.trim.Trim;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Port;

@Port
public interface CreateEstimatePort {

	List<Model> findModels();

	List<Trim> findTrimsByModel(Long modelId);

	List<Engine> findEnginesByTrim(Long trimId);

	List<BodyType> findBodyTypesByModel(Long modelId);

	List<WheelDrive> findWheelDrivesByTrim(Long trimId);

	List<Long> findUnselectableWheelDriveIdsByEngine(Long engineId);

	List<ExteriorColor> findExteriorColorsByTrim(Long trimId);

	List<InteriorColor> findInteriorColorsByTrim(Long trimId);

	List<Long> findUnselectableInteriorColorIdsByExteriorColor(Long exteriorColorId);

	List<Option> findOptionsByTrim(Long trimId);

	List<Long> findUnselectableOptionIdsByEngine(Long engineId);

	List<Long> findUnselectableOptionIdsByWheelDrive(Long wheelDriveId);

	List<Long> findUnselectableOptionIdsByBodyType(Long bodyTypeId);

	List<Long> findUnselectableOptionIdsByInteriorColor(Long interiorColorId);

	UUID createEstimate(
		Long modelId, Long trimId, Long engineId, Long wheelDriveId, Long bodyTypeId,
		Long exteriorColorId, Long interiorColorId, List<Long> selectedOptionIds, List<Long> considerOptionIds
	);
}
