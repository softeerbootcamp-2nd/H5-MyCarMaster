package softeer.be_my_car_master.infrastructure.jpa.estimate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import softeer.be_my_car_master.domain.option.Option;
import softeer.be_my_car_master.infrastructure.jpa.option.entity.OptionEntity;

@Entity
@Table(name = "estimate_consider_option")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimateConsiderOptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "estimate_id")
	private EstimateEntity estimate;

	@ManyToOne
	@JoinColumn(name = "estimate_consider_option_id")
	private OptionEntity estimateConsiderOption;

	public static EstimateConsiderOptionEntity create(EstimateEntity estimateEntity, OptionEntity optionEntity) {
		return EstimateConsiderOptionEntity.builder()
			.estimate(estimateEntity)
			.estimateConsiderOption(optionEntity)
			.build();
	}

	public Option toOption() {
		return estimateConsiderOption.toEstimateOption();
	}
}
