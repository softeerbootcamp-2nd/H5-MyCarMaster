package softeer.be_my_car_master.infrastructure.jpa.option.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.domain.option.Tag;
import softeer.be_my_car_master.infrastructure.jpa.tag.entity.TagEntity;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;

@Entity
@Table(name = "additional_trim_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdditionalTrimOptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ratio")
	private Integer ratio;

	@Column(name = "price", nullable = false)
	private Integer price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trim_id")
	private TrimEntity trim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private OptionEntity option;

	public Option toOption() {
		TagEntity tagEntity = option.getTag();
		Tag tag = tagEntity.toTag();

		List<Option> subOptions = option.getSubOptions().stream()
			.map(SuperSubEntity::getSubOption)
			.map(OptionEntity::toOption)
			.collect(Collectors.toList());

		return Option.builder()
			.id(id)
			.name(option.getName())
			.category(option.getCategory())
			.summary(option.getSummary())
			.description(option.getDescription())
			.imgUrl(option.getImgUrl())
			.price(price)
			.ratio(ratio)
			.isSuper(option.getIsSuper())
			.subOptions(subOptions)
			.tag(tag)
			.build();
	}
}
