package softeer.be_my_car_master.application.consult.exception;

import softeer.be_my_car_master.global.exception.MyCarMasterException;
import softeer.be_my_car_master.global.response.ResponseStatus;

public class InvalidCarMasterIdException extends MyCarMasterException {

	public static final MyCarMasterException EXCEPTION = new InvalidCarMasterIdException();

	private InvalidCarMasterIdException() {
		super(ResponseStatus.INVALID_ESTIMATE_ID);
	}
}
