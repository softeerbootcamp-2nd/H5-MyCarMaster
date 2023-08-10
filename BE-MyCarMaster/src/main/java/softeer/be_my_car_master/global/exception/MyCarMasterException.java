package softeer.be_my_car_master.global.exception;

import lombok.Getter;
import softeer.be_my_car_master.global.response.ResponseStatus;

@Getter
public class MyCarMasterException extends RuntimeException {

	private ResponseStatus responseStatus;

	@Override
	public String getMessage() {
		return responseStatus.getMessage();
	}
}
