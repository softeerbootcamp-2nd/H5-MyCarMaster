package softeer.be_my_car_master.domain.wheel_dirve;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import softeer.be_my_car_master.global.annotation.Domain;

@Domain
@Getter
@Builder
@AllArgsConstructor
public class WheelDrive {
	private Long id;
	private String name;
	private String description;
	private Integer ratio;
	private Integer price;
	private String imgUrl;

	public boolean isSelectable(List<Long> unselectableWheelDriveIds) {
		return !unselectableWheelDriveIds.contains(id);
	}

	public boolean isRightWheelDrive(Long engineId, Integer enginePrice) {
		return engineId.equals(id) && enginePrice.equals(price);
	}
}
