package softeer.be_my_car_master.application.engine.exception;

import softeer.be_my_car_master.global.exception.MyCarMasterException;
import softeer.be_my_car_master.global.response.ResponseStatus;

public class InvalidOptionException extends MyCarMasterException {

	public static final MyCarMasterException EXCEPTION = new InvalidOptionException();

	private InvalidOptionException() {
		super(ResponseStatus.INVALID_OPTION);
	}
}