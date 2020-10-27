//package br.com.softplan.scpbackend.exception.advice;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import br.com.softplan.scpbackend.exception.ScpNegocioException;
//
//@ControllerAdvice
//public class ScpNegocioExceptionAdvice {
//
//	@ResponseBody
//	@ExceptionHandler(ScpNegocioException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	String scpNegocioExceptionHandler(ScpNegocioException e) {
//		return e.getMessage();
//	}
//
//}
