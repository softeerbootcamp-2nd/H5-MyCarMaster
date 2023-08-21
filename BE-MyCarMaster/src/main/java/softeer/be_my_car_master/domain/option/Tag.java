package softeer.be_my_car_master.domain.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Tag {

	private Long id;
	private String name;
	private String imgUrl;
	private Boolean isMultiSelectable;
}
