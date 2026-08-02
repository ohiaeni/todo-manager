package com.todomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TodomanagerApplication クラスは、Spring Boot アプリケーションのエントリーポイントです。
 */
@SpringBootApplication
public class TodomanagerApplication {

  /**
   * アプリケーションを起動するためのメインメソッドです。
   * 
   * @param args コマンドライン引数
   */
  public static void main(String[] args) {
    SpringApplication.run(TodomanagerApplication.class, args);
  }

}
