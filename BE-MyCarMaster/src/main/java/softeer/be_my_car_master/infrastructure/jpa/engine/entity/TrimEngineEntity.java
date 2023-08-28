package softeer.be_my_car_master.infrastructure.jpa.engine.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import softeer.be_my_car_master.domain.engine.Engine;
import softeer.be_my_car_master.infrastructure.jpa.trim.entity.TrimEntity;

@Entity
@Table(name = "trim_engine")
public class TrimEngineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trim_id")
	private TrimEntity trim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "engine_id")
	private EngineEntity engine;

	public Engine toEngine() {
		return engine.toEngine();
	}
}
