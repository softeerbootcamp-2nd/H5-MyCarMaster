package softeer.be_my_car_master.global.response;

import lombok.Getter;

@Getter
public enum ResponseStatus {

	SUCCESS(2000, "요청에 성공하셨습니다."),

	BAD_REQUEST(4000, "잘못된 요청입니다"),

	INTERNAL_SERVER_ERROR(5000, "서버 내부에서 문제가 발생했습니다.");

	private final int code;

	private final String message;

	ResponseStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
