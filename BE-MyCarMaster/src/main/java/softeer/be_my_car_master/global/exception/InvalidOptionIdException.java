package softeer.be_my_car_master.global.exception;

import softeer.be_my_car_master.global.response.ResponseStatus;

public class InvalidOptionIdException extends MyCarMasterException {
	public InvalidOptionIdException() {
		super(ResponseStatus.INVALID_OPTION_ID);
	}
}
