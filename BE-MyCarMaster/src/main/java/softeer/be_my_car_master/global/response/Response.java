package softeer.be_my_car_master.global.response;

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

	public Response(T result) {
		this.code = ResponseStatus.SUCCESS.getCode();
		this.message = ResponseStatus.SUCCESS.getMessage();
		this.result = result;
	}

	public Response(ResponseStatus responseStatus) {
		this.code = responseStatus.getCode();
		this.message = responseStatus.getMessage();
	}

	public Response(ResponseStatus responseStatus, T result) {
		this.code = responseStatus.getCode();
		this.message = responseStatus.getMessage();
		this.result = result;
	}

	public static <T> Response createSuccessResponse(T result) {
		return new Response(result);
	}

	public static <T> Response createSuccessResponse() {
		return new Response(ResponseStatus.SUCCESS);
	}

	public static <T> Response createErrorResponse(ResponseStatus responseStatus) {
		return new Response(responseStatus);
	}

	public static <T> Response createErrorResponse(ResponseStatus responseStatus, T result) {
		return new Response(responseStatus, result);
	}
}
