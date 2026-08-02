package com.todomanager.presentation.controller;

import com.todomanager.application.UserRegistrationRequest;
import com.todomanager.application.UserRegistrationResponse;
import com.todomanager.application.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザー登録に関するAPIエンドポイントを提供するコントローラークラスです。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRegistrationController {
  private final UserRegistrationService userRegistrationService;

  /**
   * 新しいユーザーを登録します。
   * 
   * @param request ユーザー登録リクエストデータ
   * @return ユーザー登録レスポンスデータ
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserRegistrationResponse register(@RequestBody UserRegistrationRequest request) {
    return userRegistrationService.register(request);
  }
}
