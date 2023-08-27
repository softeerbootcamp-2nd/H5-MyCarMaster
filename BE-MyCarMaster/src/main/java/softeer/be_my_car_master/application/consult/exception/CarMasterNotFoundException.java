package softeer.be_my_car_master.application.consult.exception;

import softeer.be_my_car_master.global.exception.MyCarMasterException;
import softeer.be_my_car_master.global.response.ResponseStatus;

public class CarMasterNotFoundException extends MyCarMasterException {

	public static final MyCarMasterException EXCEPTION = new CarMasterNotFoundException();

	private CarMasterNotFoundException() {
		super(ResponseStatus.CAR_MASTER_NOT_FOUND);
	}
}
