# todo-listing-api Specification

## Purpose
クライアントが内部実装に依存せずにタスクリストを描画できるよう、全 TODO 項目を取得するための安定した API 契約を定義する。

## Requirements

### Requirement: 全 TODO 項目を取得する
システムは、永続化された全 TODO 項目を単一レスポンスで返す HTTP エンドポイントを SHALL 提供する。

#### Scenario: TODO が存在する
- **WHEN** クライアントが全 TODO 項目取得リクエストを送信する
- **THEN** システムは全 TODO 項目を含む HTTP 成功レスポンスを返す

### Requirement: TODO が存在しない場合は空の一覧を返す
システムは、永続化された TODO 項目が存在しない場合でも、成功レスポンスとして空の一覧を MUST 返す。

#### Scenario: TODO が存在しない
- **WHEN** クライアントが全 TODO 項目取得リクエストを送信し、ストレージが空である
- **THEN** システムは空の一覧を含む HTTP 成功レスポンスを返す

### Requirement: 一覧レスポンスに TODO 項目のフィールドを含める
システムは、一覧レスポンスのペイロードに各 TODO 項目の identifier、title、completion status を SHALL 含める。

#### Scenario: レスポンス項目構造を確認する
- **WHEN** クライアントが一覧レスポンスを受け取る
- **THEN** ペイロード内の各 TODO 項目に identifier、title、completion status が含まれる
