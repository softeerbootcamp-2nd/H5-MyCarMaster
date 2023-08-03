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
		this.code = Code.SUCCESS.getCode();
		this.message = Code.SUCCESS.getMessage();
		this.result = result;
	}

	// 오류 발생
	public Response(Code code) {
		this.code = code.getCode();
		this.message = code.getMessage();
	}

	public Response(Code code, T result) {
		this.code = code.getCode();
		this.message = code.getMessage();
		this.result = result;
	}
}
