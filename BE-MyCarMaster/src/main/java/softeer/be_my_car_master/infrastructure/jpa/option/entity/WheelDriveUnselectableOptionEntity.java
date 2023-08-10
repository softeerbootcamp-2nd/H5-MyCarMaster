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
import softeer.be_my_car_master.infrastructure.jpa.wheel_drive.entity.WheelDriveEntity;

@Entity
@Table(name = "wheel_drive_unselectable_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WheelDriveUnselectableOptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wheel_drive_id")
	private WheelDriveEntity wheelDrive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private OptionEntity option;
}
