package softeer.be_my_car_master.api.estimate.exception;

import softeer.be_my_car_master.global.exception.MyCarMasterException;
import softeer.be_my_car_master.global.response.ResponseStatus;

public class InvalidEstimationException extends MyCarMasterException {

	public static final MyCarMasterException EXCEPTION = new InvalidEstimationException();

	private InvalidEstimationException() {
		super(ResponseStatus.INVALID_ESTIMATION);
	}
}
