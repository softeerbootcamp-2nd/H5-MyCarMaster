package softeer.be_my_car_master.infrastructure.jpa.tag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.option.Tag;

@Entity
@Table(name = "tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "is_multi_selectable", nullable = false)
	private Boolean isMultiSelectable;

	public Tag toTag() {
		return Tag.builder()
			.id(id)
			.name(name)
			.imgUrl(imgUrl)
			.isMultiSelectEnabled(isMultiSelectable)
			.build();
	}
}
