package softeer.be_my_car_master.infrastructure.jpa.tag.adaptor;

import java.util.List;

import lombok.RequiredArgsConstructor;
import softeer.be_my_car_master.api.option.usecase.port.TagPort;
import softeer.be_my_car_master.global.annotation.Adaptor;
import softeer.be_my_car_master.infrastructure.jpa.tag.repository.TagJpaRepository;

@Adaptor
@RequiredArgsConstructor
public class TagJpaAdaptor implements TagPort {

	private final TagJpaRepository tagJpaRepository;

	@Override
	public List<String> findSingleSelectableTags() {
		return tagJpaRepository.findSingleSelectableTagIdsByIsMultiSelectable();
	}
}
