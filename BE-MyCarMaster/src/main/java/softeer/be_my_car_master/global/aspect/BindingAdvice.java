package softeer.be_my_car_master.global.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import softeer.be_my_car_master.global.exception.BindingParamException;

@Aspect
@Component
public class BindingAdvice {

	@Around("execution(* softeer.be_my_car_master.application..*Controller.*(.., @javax.validation.Valid (*), ..))")
	public Object validationAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();

		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if (bindingResult.hasErrors()) {
					throw new BindingParamException(bindingResult.getFieldErrors());
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
