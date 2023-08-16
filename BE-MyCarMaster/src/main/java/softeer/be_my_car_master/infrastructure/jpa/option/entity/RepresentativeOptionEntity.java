package softeer.be_my_car_master.infrastructure.jpa.option.entity;

import java.util.List;
import java.util.stream.Collectors;

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
import softeer.be_my_car_master.infrastructure.jpa.model.entity.ModelEntity;

@Entity
@Table(name = "representative_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RepresentativeOptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ModelEntity model;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private OptionEntity option;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "applied_option_id")
	private OptionEntity appliedOption;

	public Option toRepresentativeOption() {
		List<Option> subOptions = option.getSubOptions().stream()
			.map(SuperSubEntity::getSubOption)
			.map(OptionEntity::toOption)
			.collect(Collectors.toList());

		return Option.builder()
			.id(option.getId())
			.name(option.getName())
			.imgUrl(option.getImgUrl())
			.summary(option.getSummary())
			.description(option.getDescription())
			.isSuper(option.getIsSuper())
			.subOptions(subOptions)
			.build();
	}

	public Option toSimpleOption() {
		return appliedOption.toSimpleOption();
	}
}
