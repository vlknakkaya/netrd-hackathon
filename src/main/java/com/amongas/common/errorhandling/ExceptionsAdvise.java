package com.amongas.common.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amongas.common.TextToSpeechAlgorithm;
import com.amongas.common.errorhandling.exception.ConditionNameNotFoundException;
import com.amongas.common.errorhandling.exception.NumberNotFoundException;
import com.amongas.common.errorhandling.exception.ProfileIdNotFoundException;

@RestControllerAdvice
public class ExceptionsAdvise {

	@ExceptionHandler(value = NumberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNumberNotFoundException(NumberNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.NUMBER_NOT_FOUND);
		errorResponse.setErrorMessage(ex.getMessage());
		errorResponse.setErrorVoice(TextToSpeechAlgorithm.test(ex.getMessage()));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ProfileIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProfileIdNotFoundException(ProfileIdNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.ID_NOT_FOUND);
		errorResponse.setErrorMessage(ex.getMessage());
		errorResponse.setErrorVoice(TextToSpeechAlgorithm.test(ex.getMessage()));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ConditionNameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleConfigNotFoundException(ConditionNameNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.CONDITION_NAME_NOT_FOUND);
		errorResponse.setErrorMessage(ex.getMessage());
		errorResponse.setErrorVoice(TextToSpeechAlgorithm.test(ex.getMessage()));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
