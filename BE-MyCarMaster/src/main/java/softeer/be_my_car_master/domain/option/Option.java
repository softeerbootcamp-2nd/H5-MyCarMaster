package softeer.be_my_car_master.domain.option;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Option {

	private Long id;
	private String name;
	private Category category;
	private String summary;
	private String description;
	private String imgUrl;
	private Integer price;
	private Integer ratio;
	private Boolean isSuper;
	private List<Option> subOptions;
	private Tag tag;

	public String getTagName() {
		return tag == null ? null : tag.getName();
	}

	public Boolean hasSubOption() {
		return isSuper;
	}

	public static Set<Long> combineUnselectableOptionIds(List<Long>... unselectableOptionIdsLists) {
		Set<Long> combinedSet = new HashSet<>();
		for (List<Long> list : unselectableOptionIdsLists) {
			combinedSet.addAll(list);
		}
		return combinedSet;
	}

	public static List<Option> filterOptionsByUnselectableIds(
		List<Option> selectableOptions,
		Set<Long> unselectableOptionIdsSet
	) {
		return selectableOptions.stream()
			.filter(option -> !unselectableOptionIdsSet.contains(option.getId()))
			.collect(Collectors.toList());
	}
}
