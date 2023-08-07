package softeer.bemycarmaster.api.domain.trim.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import softeer.bemycarmaster.api.domain.trim.domain.Trim;
import softeer.bemycarmaster.api.domain.trim.usecase.port.TrimPort;
import softeer.bemycarmaster.api.global.annotation.Adaptor;

@Adaptor
@RequiredArgsConstructor
public class TrimJpaAdaptor implements TrimPort {

	private final TrimJpaRepository trimJpaRepository;

	@Override
	public List<Trim> findTrims(Long modelId) {
		return trimJpaRepository.findTrimsByModelId(modelId).stream()
			.map(TrimEntity::toTrim)
			.collect(Collectors.toList());
	}
}
