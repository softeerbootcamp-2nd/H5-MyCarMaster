package softeer.be_my_car_master.application.option.usecase.get_trim_default_options;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.TrimDefaultOptionEntity;
import softeer.be_my_car_master.infrastructure.jpa.option.repository.TrimDefaultOptionJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class GetTrimDefaultOptionsAdaptor implements GetTrimDefaultOptionsPort {

	private final TrimDefaultOptionJpaRepository trimDefaultOptionJpaRepository;

	@Override
	public List<Option> findDefaultOptionsByTrim(Long trimId) {
		return trimDefaultOptionJpaRepository.findAllByTrimId(trimId).stream()
			.map(TrimDefaultOptionEntity::toDefaultOption)
			.collect(Collectors.toList());
	}
}
