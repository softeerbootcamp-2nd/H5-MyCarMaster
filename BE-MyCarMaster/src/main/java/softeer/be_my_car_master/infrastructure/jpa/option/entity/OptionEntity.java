package softeer.be_my_car_master.infrastructure.jpa.option.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.option.Category;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.option.Tag;
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;
import softeer.be_my_car_master.infrastructure.jpa.tag.entity.TagEntity;

@Entity
@Table(name = "`option`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "summary")
	private String summary;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "category", nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "is_super", nullable = false)
	private Boolean isSuper;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ModelEntity model;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	private TagEntity tag;

	@OneToMany(mappedBy = "subOption")
	private List<SuperSubEntity> superOptions = new ArrayList<>();

	@OneToMany(mappedBy = "superOption")
	private List<SuperSubEntity> subOptions = new ArrayList<>();

	public Tag getTag() {
		return tag == null ? null : tag.toTag();
	}

	public Option toOption() {
		return Option.builder()
			.id(id)
			.name(name)
			.summary(summary)
			.description(description)
			.category(category)
			.imgUrl(imgUrl)
			.price(price)
			.isSuper(isSuper)
			.tag(getTag())
			.build();
	}

	public Option toSimpleOption() {
		return Option.builder()
			.id(id)
			.name(name)
			.price(price)
			.build();
	}

	public Option toEstimateOption() {
		return Option.builder()
			.name(name)
			.price(price)
			.imgUrl(imgUrl)
			.category(category)
			.build();
	}
}
