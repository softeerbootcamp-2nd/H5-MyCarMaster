package softeer.be_my_car_master.global.response;

import lombok.Getter;

@Getter
public enum ResponseStatus {

	SUCCESS(2000, "요청에 성공하셨습니다."),

	BAD_REQUEST(4000, "잘못된 요청입니다"),

	INVALID_OPTION_BY_TRIM(4100, "해당 트림에서 선택 불가능한 옵션이 포함되어 있습니다."),

	INVALID_ESTIMATE(4200, "잘못된 견적서입니다. 견적서 내용을 다시 확인해주세요."),
	INVALID_ESTIMATE_ID(4201, "해당 ID와 일치하는 견적서가 존재하지 않습니다."),

	INTERNAL_SERVER_ERROR(5000, "서버 내부에서 문제가 발생했습니다.");

	private final int code;

	private final String message;

	ResponseStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
