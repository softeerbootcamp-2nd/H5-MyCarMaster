package softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import softeer.be_my_car_master.infrastructure.jpa.engine.entity.EngineEntity;

@Entity
public class UnselectableEngineWheelDriveEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "engine_id")
	private EngineEntity engine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wheel_drive_id")
	private WheelDriveEntity wheelDrive;
}
