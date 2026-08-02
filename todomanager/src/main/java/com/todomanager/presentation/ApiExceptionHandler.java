package com.todomanager.presentation;

import com.todomanager.domain.TaskNotFoundException;
import com.todomanager.domain.TaskOwnershipException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler({IllegalArgumentException.class, MissingRequestHeaderException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBadRequest(Exception exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ExceptionHandler(TaskOwnershipException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handleTaskOwnership(TaskOwnershipException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ExceptionHandler(TaskNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleTaskNotFound(TaskNotFoundException exception) {
    return new ErrorResponse(exception.getMessage());
  }
}
