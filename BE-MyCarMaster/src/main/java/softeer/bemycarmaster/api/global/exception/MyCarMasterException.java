package softeer.bemycarmaster.api.global.exception;

import lombok.Getter;
import softeer.bemycarmaster.api.global.response.ResponseStatus;

@Getter
public class MyCarMasterException extends RuntimeException {

	private ResponseStatus responseStatus;

	@Override
	public String getMessage() {
		return responseStatus.getMessage();
	}
}
