package softeer.be_my_car_master.infrastructure.jpa.option.entity;

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
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;

@Entity
@Table(name = "trim_default_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrimDefaultOptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trim_id")
	private TrimEntity trim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private OptionEntity option;

	public Option toDefaultOption() {
		return Option.builder()
			.id(option.getId())
			.name(option.getName())
			.category(option.getCategory())
			.description(option.getDescription())
			.imgUrl(option.getImgUrl())
			.build();
	}
}
