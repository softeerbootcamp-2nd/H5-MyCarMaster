package softeer.be_my_car_master.global.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import softeer.be_my_car_master.global.response.Response;
import softeer.be_my_car_master.global.response.ResponseStatus;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 *  클라이언트 예외
	 */
	@ExceptionHandler(BindException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<?> inputValueInvalidExceptionHandler(BindException error) {
		List<String> errorMessages = error.getBindingResult().getAllErrors().stream()
			.map(objectError -> objectError.getDefaultMessage())
			.collect(Collectors.toList());

		return Response.createErrorResponse(ResponseStatus.BAD_REQUEST, errorMessages);
	}

	@ExceptionHandler(BindingParamException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<?> bindingParamExceptionHandler(BindingParamException error) {
		List<String> errorMessages = error.getFieldErrors().stream()
			.map(objectError -> objectError.getDefaultMessage())
			.collect(Collectors.toList());

		return Response.createErrorResponse(ResponseStatus.BAD_REQUEST, errorMessages);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response<?> httpFormatErrorHandler() {
		return Response.createErrorResponse(ResponseStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MyCarMasterException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<?> myCarMasterExceptionHandler(MyCarMasterException error) {
		return Response.createErrorResponse(error.getResponseStatus());
	}

	/**
	 *  서버 에러
	 */
	@ExceptionHandler(Exception.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response<?> internalServerErrorHandler(Exception error) {
		log.error("[{}] {}", error.getClass().getSimpleName(), error.getMessage());
		return Response.createErrorResponse(ResponseStatus.INTERNAL_SERVER_ERROR);
	}
}
