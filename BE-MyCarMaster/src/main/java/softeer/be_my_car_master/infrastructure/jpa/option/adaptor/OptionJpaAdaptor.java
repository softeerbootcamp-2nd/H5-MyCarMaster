package softeer.be_my_car_master.infrastructure.jpa.option.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.usecase.port.OptionPort;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.AdditionalTrimOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.AdditionalTrimOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.UnselectableBodyTypeOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.UnselectableEngineOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.UnselectableInteriorColorOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.UnselectableWheelDriveOptionJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class OptionJpaAdaptor implements OptionPort {

	private final AdditionalTrimOptionJpaRepository additionalTrimOptionJpaRepository;
	private final UnselectableEngineOptionJpaRepository unselectableEngineOptionJpaRepository;
	private final UnselectableWheelDriveOptionJpaRepository unselectableWheelDriveOptionJpaRepository;
	private final UnselectableBodyTypeOptionJpaRepository unselectableBodyTypeOptionJpaRepository;
	private final UnselectableInteriorColorOptionJpaRepository unselectableInteriorColorOptionJpaRepository;

	@Override
	public List<Option> findSelectableOptionsByTrimId(Long trimId) {
		List<AdditionalTrimOptionEntity> additionalTrimOptionEntities =
			additionalTrimOptionJpaRepository.findAllByTrimId(trimId);

		// todo: 불필요해보이는 로직. 데이터 삽입후 테스트 필요
		// additionalTrimOptionEntities.stream()
		// 	.map(AdditionalTrimOptionEntity::getOption)
		// 	.forEach(OptionEntity::getSubOptions);

		return additionalTrimOptionEntities.stream()
			.map(AdditionalTrimOptionEntity::toOption)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findUnselectableOptionIdsByEngineId(Long engineId) {
		return unselectableEngineOptionJpaRepository.findUnselectableOptionIdsByEngineId(engineId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByWheelDriveId(Long wheelDriveId) {
		return unselectableWheelDriveOptionJpaRepository.findUnselectableOptionIdsByWheelDriveId(wheelDriveId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByBodyTypeId(Long bodyTypeId) {
		return unselectableBodyTypeOptionJpaRepository.findUnselectableOptionIdsByBodyTypeId(bodyTypeId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByInteriorColorId(Long interiorColorId) {
		return unselectableInteriorColorOptionJpaRepository.findUnselectableOptionIdsByInteriorColorId(interiorColorId);
	}
}
