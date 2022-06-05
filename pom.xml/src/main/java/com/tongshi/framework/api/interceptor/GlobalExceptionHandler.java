package com.tongshi.framework.api.interceptor;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountExpiredException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import com.tongshi.framework.api.constant.HttpStatusConstants;
import com.tongshi.framework.api.vo.ResponseVo;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    
	/**
	*  校验错误拦截处理
	*
	* @param e 错误信息集合
	* @return 错误信息
	*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseVo validationBodyException(MethodArgumentNotValidException e){
		BindingResult result = e.getBindingResult();
		StringBuilder errorMessage = new StringBuilder();
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			errors.forEach(p ->{
				FieldError fieldError = (FieldError) p;
				log.error("数据校验错误 : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
						"},errorMessage{"+fieldError.getDefaultMessage()+"}");
				errorMessage.append(fieldError.getDefaultMessage());
			});
		}
		return ResponseVo.error(HttpStatusConstants.VALID_ERROR, errorMessage.toString());

	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseVo validationConstraintViolationException(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
		return ResponseVo.error(HttpStatusConstants.VALID_ERROR, message);

	}
	
	/**
     * 校验错误拦截处理
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseVo bindException(BindException e) {
    	List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
      	String message = fieldErrors.stream()
      			.map(o -> o.getDefaultMessage())
      			.collect(Collectors.joining(";"));
      	return ResponseVo.error(HttpStatusConstants.VALID_ERROR, message);
    }

	/**
	 * 请求方式不支持
	 * @param e
	 * @return
	 */
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseVo handleException(HttpRequestMethodNotSupportedException e) {
       log.error(e.getMessage(), e);
       return ResponseVo.error(HttpStatusConstants.BAD_METHOD, "不支持请求方式:" + e.getMethod());
   }
	
   // 请求的数据格式错误的处理
   @ExceptionHandler(HttpMessageNotReadableException.class)
   public ResponseVo handleException(HttpMessageNotReadableException e) {
       log.error(e.getMessage(), e);
       return ResponseVo.error(HttpStatusConstants.ILLEGAL_ARGUMENT, "传入数据格式错误");
   }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseVo handlerIllegalArgumentException(IllegalArgumentException e) {
        return ResponseVo.error(HttpStatusConstants.ILLEGAL_ARGUMENT, "参数错误:" + e.getMessage());
    }
	
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseVo handleAuthorizationException(AccessDeniedException e) {
        return ResponseVo.error(HttpStatusConstants.FORBIDDEN, "没有权限，请联系管理员授权");
    }
    
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseVo handleAccountExpiredException(AccountExpiredException e) {
        return ResponseVo.error(e.getMessage());
    }
    
    /**
     * Sql完整性约束异常
     * @param e 异常
     * @return ResponseVo
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseVo handleDuplicateKeyException(DuplicateKeyException e) {
    	log.error(e.getMessage());
        return ResponseVo.error("主键已经存在.");
    }
    
    /**
     * Sql完整性约束异常
     * @param e 异常
     * @return ResponseVo
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseVo handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
    	log.error(e.getMessage());
        return ResponseVo.error("数据库约束错误.");
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseVo handlerNoFoundException(Exception e) {
        return ResponseVo.error(HttpStatusConstants.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseVo handleException(Exception e) {
        return ResponseVo.error(e.getMessage());
    }
    
}
