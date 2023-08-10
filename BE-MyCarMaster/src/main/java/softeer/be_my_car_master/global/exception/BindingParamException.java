package softeer.be_my_car_master.global.exception;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BindingParamException extends RuntimeException {

	private List<FieldError> fieldErrors;
}
