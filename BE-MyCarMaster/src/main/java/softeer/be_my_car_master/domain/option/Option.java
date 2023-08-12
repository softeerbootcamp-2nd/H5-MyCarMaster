package softeer.be_my_car_master.domain.option;

import java.util.List;

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
	private String category;
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
}
