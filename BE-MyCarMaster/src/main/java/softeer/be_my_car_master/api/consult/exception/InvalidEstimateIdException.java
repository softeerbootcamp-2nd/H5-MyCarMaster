package softeer.be_my_car_master.api.consult.exception;

import softeer.be_my_car_master.global.exception.MyCarMasterException;
import softeer.be_my_car_master.global.response.ResponseStatus;

public class InvalidEstimateIdException extends MyCarMasterException {

	public static final MyCarMasterException EXCEPTION = new InvalidEstimateIdException();

	private InvalidEstimateIdException() {
		super(ResponseStatus.INVALID_ESTIMATE_ID);
	}
}
