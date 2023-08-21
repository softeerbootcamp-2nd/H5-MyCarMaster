package softeer.be_my_car_master.application.option.usecase.get_unselectable_options_by_engine;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimAdditionalOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.EngineUnselectableOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimAdditionalOptionJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetUnselectableOptionsByEngineAdaptor implements GetUnselectableOptionsByEnginePort {

	private final TrimAdditionalOptionJpaRepository trimAdditionalOptionJpaRepository;
	private final EngineUnselectableOptionJpaRepository engineUnselectableOptionJpaRepository;


	@Override
	public List<Long> findOptionIdsByTrim(Long trimId) {
		return trimAdditionalOptionJpaRepository.findUnselectableOptionIdsByTrimId(trimId);
	}

	@Override
	public List<Long> findUnselectableOptionIdsByEngine(Long engineId) {
		return engineUnselectableOptionJpaRepository.findUnselectableOptionIdsByEngineId(engineId);
	}

	@Override
	public List<Option> findUnselectableOptions(Long trimId, List<Long> optionIds) {
		return trimAdditionalOptionJpaRepository.findAllByIdIn(trimId, optionIds).stream()
			.map(TrimAdditionalOptionEntity::toSimpleOption)
			.collect(Collectors.toList());
	}
}
