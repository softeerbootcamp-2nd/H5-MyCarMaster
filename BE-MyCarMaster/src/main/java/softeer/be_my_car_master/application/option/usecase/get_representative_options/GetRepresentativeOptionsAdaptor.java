package softeer.be_my_car_master.application.option.usecase.get_representative_options;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.RepresentativeOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.RepresentativeOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimAdditionalOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimDefaultOptionJpaRepository;
import softeer.be_my_car_master.infrastructure.jpa.trim.repository.TrimJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetRepresentativeOptionsAdaptor implements GetRepresentativeOptionsPort {

	private final RepresentativeOptionJpaRepository representativeOptionJpaRepository;
	private final TrimJpaRepository trimJpaRepository;
	private final TrimAdditionalOptionJpaRepository trimAdditionalOptionJpaRepository;
	private final TrimDefaultOptionJpaRepository trimDefaultOptionJpaRepository;

	@Override
	@Transactional
	public List<Option> findRepresentativeOptionsByModel(Long modelId) {
		return representativeOptionJpaRepository.findAllByModelId(modelId).stream()
			.map(RepresentativeOptionEntity::toRepresentativeOption)
			.collect(Collectors.toList());
	}

	@Override
	public List<Option> findAppliedOptionsByModelAndOptions(Long modelId, List<Long> optionIds) {
		return representativeOptionJpaRepository.findAppliedOptionsByModelIdAndOptionIds(modelId, optionIds).stream()
			.map(RepresentativeOptionEntity::toAppliedOption)
			.collect(Collectors.toList());
	}

	@Override
	public List<Long> findTrimIdsByModel(Long modelId) {
		return trimJpaRepository.findTrimIdsByModelId(modelId);
	}

	@Override
	public List<Long> findAdditionalTrimIdsByOption(Long optionId) {
		return trimAdditionalOptionJpaRepository.findTrimIdsByOptionId(optionId);
	}

	@Override
	public List<Long> findDefaultTrimIdsByOption(Long optionId) {
		return trimDefaultOptionJpaRepository.findTrimIdsByOptionId(optionId);
	}
}
