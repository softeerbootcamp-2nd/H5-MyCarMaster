package softeer.bemycarmaster.api.global.response;

import lombok.Getter;

@Getter
public enum ResponseStatus {

	SUCCESS(2000, "요청에 성공하셨습니다.");

	private int code;

	private String message;

	ResponseStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
