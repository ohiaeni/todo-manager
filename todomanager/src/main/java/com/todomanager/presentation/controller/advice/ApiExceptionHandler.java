package com.todomanager.presentation.controller.advice;

import com.todomanager.domain.exception.TaskNotFoundException;
import com.todomanager.domain.exception.TaskOwnershipException;
import com.todomanager.presentation.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * APIの例外を処理するためのハンドラークラスです。
 */
@RestControllerAdvice
public class ApiExceptionHandler {

  /**
   * Bad Request (400) エラーを処理します。
   * 
   * @param exception 例外オブジェクト
   * @return エラーレスポンス
   */
  @ExceptionHandler({IllegalArgumentException.class, MissingRequestHeaderException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBadRequest(Exception exception) {
    return new ErrorResponse(exception.getMessage());
  }

  /**
   * Forbidden (403) エラーを処理します。
   * 
   * @param exception 例外オブジェクト
   * @return エラーレスポンス
   */
  @ExceptionHandler(TaskOwnershipException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handleTaskOwnership(TaskOwnershipException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  /**
   * Not Found (404) エラーを処理します。
   * 
   * @param exception 例外オブジェクト
   * @return エラーレスポンス
   */
  @ExceptionHandler(TaskNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleTaskNotFound(TaskNotFoundException exception) {
    return new ErrorResponse(exception.getMessage());
  }
}
