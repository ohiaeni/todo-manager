## MODIFIED Requirements

### Requirement: 全 TODO 項目を取得する
システムは、認証済みユーザーのコンテキストに紐づく TODO 項目のみを単一レスポンスで返す HTTP エンドポイントを MUST 提供する。

#### Scenario: 自分の TODO が存在する
- 認証済みユーザーが TODO 項目取得リクエストを送信した場合
- システムは当該ユーザーに紐づく TODO 項目のみを含む HTTP 成功レスポンスを返すこと

### Requirement: TODO が存在しない場合は空の一覧を返す
システムは、認証済みユーザーに紐づく TODO 項目が存在しない場合でも、成功レスポンスとして空の一覧を MUST 返す。

#### Scenario: 自分の TODO が存在しない
- 認証済みユーザーが TODO 項目取得リクエストを送信し、当該ユーザーのストレージが空である場合
- システムは空の一覧を含む HTTP 成功レスポンスを返すこと

### Requirement: 一覧レスポンスに TODO 項目のフィールドを含める
システムは、一覧レスポンスのペイロードに各 TODO 項目の identifier、title、completion status を MUST 含める。

#### Scenario: レスポンス項目構造を確認する
- 認証済みユーザーが一覧レスポンスを受け取った場合
- ペイロード内の各 TODO 項目に identifier、title、completion status が含まれること