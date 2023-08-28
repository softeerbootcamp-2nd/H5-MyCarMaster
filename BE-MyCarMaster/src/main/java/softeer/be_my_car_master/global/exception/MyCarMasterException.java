package softeer.be_my_car_master.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import softeer.be_my_car_master.global.response.ResponseStatus;

@Getter
@AllArgsConstructor
public class MyCarMasterException extends RuntimeException {

	private ResponseStatus responseStatus;

	@Override
	public String getMessage() {
		return responseStatus.getMessage();
	}
}
