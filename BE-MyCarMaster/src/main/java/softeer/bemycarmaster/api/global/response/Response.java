package softeer.bemycarmaster.api.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private int code;

	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result;

	// 요청 성공
	public Response(T result) {
		this.code = ResponseStatus.SUCCESS.getCode();
		this.message = ResponseStatus.SUCCESS.getMessage();
		this.result = result;
	}

	// 오류 발생
	public Response(ResponseStatus responseStatus) {
		this.code = responseStatus.getCode();
		this.message = responseStatus.getMessage();
	}

	public Response(ResponseStatus responseStatus, T result) {
		this.code = responseStatus.getCode();
		this.message = responseStatus.getMessage();
		this.result = result;
	}
}
