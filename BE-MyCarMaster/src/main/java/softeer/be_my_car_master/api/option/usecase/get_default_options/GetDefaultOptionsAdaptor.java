package softeer.be_my_car_master.api.option.usecase.get_default_options;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimDefaultOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.BodyTypeUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.EngineUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimDefaultOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.WheelDriveUnselectableOptionJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetDefaultOptionsAdaptor implements GetDefaultOptionsPort {

	private final TrimDefaultOptionJpaRepository trimDefaultOptionJpaRepository;
	private final EngineUnselectableOptionJpaRepository engineUnselectableOptionJpaRepository;
	private final WheelDriveUnselectableOptionJpaRepository wheelDriveUnselectableOptionJpaRepository;
	private final BodyTypeUnselectableOptionJpaRepository bodyTypeUnselectableOptionJpaRepository;


	@Override
	public List<Option> findDefaultOptionsByTrim(Long trimId) {
		return trimDefaultOptionJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimDefaultOptionEntity::toDefaultOption)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findUnselectableOptionIdsByEngine(Long engineId) {
		return engineUnselectableOptionJpaRepository.findUnselectableOptionIdsByEngineId(engineId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByWheelDrive(Long wheelDriveId) {
		return wheelDriveUnselectableOptionJpaRepository.findUnselectableOptionIdsByWheelDriveId(wheelDriveId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByBodyType(Long bodyTypeId) {
		return bodyTypeUnselectableOptionJpaRepository.findUnselectableOptionIdsByBodyTypeId(bodyTypeId);
	}
}
