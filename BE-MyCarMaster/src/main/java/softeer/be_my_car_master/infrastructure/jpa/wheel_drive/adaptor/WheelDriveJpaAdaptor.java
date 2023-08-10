package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.wheeldrive.usecase.port.WheelDrivePort;
import softeer.be_my_car_master.domain.wheel_dirve.WheelDrive;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.TrimWheelDriveEntity;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository.EngineUnselectableWheelDriveJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.repository.TrimWheelDriveJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class WheelDriveJpaAdaptor implements WheelDrivePort {

	private final TrimWheelDriveJpaRepository trimWheelDriveJpaRepository;
	private final EngineUnselectableWheelDriveJpaRepository engineUnselectableWheelDriveJpaRepository;

	@Override
	public List<WheelDrive> findSelectableWheelDrivesByTrimId(Long trimId) {
		return trimWheelDriveJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimWheelDriveEntity::toWheelDrive)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findUnselectableWheelDriveIdsByEngineId(Long engineId) {
		return engineUnselectableWheelDriveJpaRepository
			.findUnselectableWheelDriveIdsByEngineId(engineId);
	}
}
