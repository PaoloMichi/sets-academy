package it.sets.common.web;

import org.slf4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.sets.common.exception.ApplicationUnauthorizedException;
import it.sets.common.exception.DuplicateElemException;
import it.sets.common.exception.EExceptionCode;
import it.sets.common.exception.EntityNotFoundException;
import it.sets.common.exception.FileException;
import it.sets.common.exception.FtpException;
import it.sets.common.exception.InvalidEntityFieldException;
import it.sets.common.exception.InvalidPathVariablesException;
import it.sets.common.exception.MethodNotAllowedException;
import it.sets.common.exception.MissingEntityFieldException;
import it.sets.common.exception.PasswordRecoveryException;
import it.sets.common.exception.RepositoryException;
import it.sets.common.exception.SendEmailException;
import it.sets.common.model.web.ApiError;
import it.sets.common.model.web.BaseResponse;

public abstract class AbstractControllerAdvisor extends ResponseEntityExceptionHandler {
	
	protected abstract Logger getLogger();

	//---------- CUSTOM EXCEPTIONS ----------//
	
	@ExceptionHandler(RepositoryException.class)
	public ResponseEntity<BaseResponse> handleRepositoryException(RepositoryException ex, WebRequest request) {
		getLogger().error("handleRepositoryException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<BaseResponse> handleElemNotFoundException(EntityNotFoundException ex, WebRequest request) {
		getLogger().error("handleElemNotFoundException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.NOT_FOUND, ex));
	}
	
	@ExceptionHandler(DuplicateElemException.class)
	public ResponseEntity<BaseResponse> handleDuplicateElemException(DuplicateElemException ex, WebRequest request) {
		getLogger().error("handleDuplicateElemException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(MethodNotAllowedException.class)
	public ResponseEntity<BaseResponse> handleMethodNotAllowedException(MethodNotAllowedException ex, WebRequest request) {
		getLogger().error("handleMethodNotAllowedException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex));
	}
	
	@ExceptionHandler(InvalidPathVariablesException.class)
	public ResponseEntity<BaseResponse> handleInvalidPathVariablesException(InvalidPathVariablesException ex, WebRequest request) {
		getLogger().error("handleInvalidPathVariablesException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.BAD_REQUEST, ex));
	}
	
	@ExceptionHandler(MissingEntityFieldException.class)
	public ResponseEntity<BaseResponse> handleMissingRequestFieldException(MissingEntityFieldException ex, WebRequest request) {
		getLogger().error("handleMissingRequestFieldException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.BAD_REQUEST, ex));
	}
	
	@ExceptionHandler(InvalidEntityFieldException.class)
	public ResponseEntity<BaseResponse> handleInvalidRequestFieldException(InvalidEntityFieldException ex, WebRequest request) {
		getLogger().error("handleInvalidRequestFieldException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.BAD_REQUEST, ex));
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<BaseResponse> handleFileException(FileException ex, WebRequest request) {
		getLogger().error("handleFileException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(FtpException.class)
	public ResponseEntity<BaseResponse> handleFtpException(FtpException ex, WebRequest request) {
		getLogger().error("handleFtpException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(SendEmailException.class)
	public ResponseEntity<BaseResponse> handleSendEmailException(SendEmailException ex, WebRequest request) {
		getLogger().error("handleSendEmailException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, EExceptionCode.SM_001.name(), ex));
	}
	
	@ExceptionHandler(PasswordRecoveryException.class)
	public ResponseEntity<BaseResponse> handlePasswordRecoveryException(PasswordRecoveryException ex, WebRequest request) {
		getLogger().error("handlePasswordRecoveryException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, EExceptionCode.PR_001.name(), ex.getShortMessage()));
	}
	
	@ExceptionHandler(ApplicationUnauthorizedException.class)
	public ResponseEntity<Object> handleApplicationUnauthorizedException(ApplicationUnauthorizedException ex, WebRequest request) {
		getLogger().error("handleApplicationUnauthorizedException", ex);
		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
	}
	
	//---------- NATIVE EXCEPTIONS ----------//
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		getLogger().error("handleHttpMessageNotWritable", ex);
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
		return new ResponseEntity<>(new BaseResponse(apiError), apiError.getStatus());
	}
	
	@Override
	public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		getLogger().error("handleMissingPathVariable", ex);
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
		return new ResponseEntity<>(new BaseResponse(apiError), apiError.getStatus());
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		getLogger().error("handleAccessDeniedException", ex);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<BaseResponse> handleResourceAccessException(ResourceAccessException ex, WebRequest request) {
		getLogger().error("handleResourceAccessException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<BaseResponse> handleUnsupportedOperationException(UnsupportedOperationException ex, WebRequest request) {
		getLogger().error("handleUnsupportedOperationException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.NOT_IMPLEMENTED, ex));
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<BaseResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		getLogger().error("handleEmptyResultDataAccessException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
		getLogger().error("handleRuntimeException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<BaseResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		getLogger().error("handleIllegalArgumentException", ex);
		return buildBaseResponse(new ApiError(HttpStatus.BAD_REQUEST, ex));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		getLogger().error("handleMethodArgumentNotValid", ex);
		return new ResponseEntity<>(new BaseResponse(new ApiError(HttpStatus.BAD_REQUEST, ex)), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		getLogger().error("handleHttpMessageNotReadable", ex);
		String error = "Malformed JSON request";
		return new ResponseEntity<>(new BaseResponse(new ApiError(HttpStatus.BAD_REQUEST, error, ex)), HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<BaseResponse> buildBaseResponse(ApiError apiError) {
		return new ResponseEntity<>(new BaseResponse(apiError), apiError.getStatus());
	}
	
}