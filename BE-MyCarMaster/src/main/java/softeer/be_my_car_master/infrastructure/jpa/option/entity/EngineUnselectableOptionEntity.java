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
import softeer.be_my_car_master.infrastructure.jpa.engine.entity.EngineEntity;

@Entity
@Table(name = "engine_unselectable_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EngineUnselectableOptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "engine_id")
	private EngineEntity engine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private OptionEntity option;
}
