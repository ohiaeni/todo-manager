## テスト棚卸し結果

### 層別の現状

- プレゼンテーション層
  - 対象: `TasksController`, `UserRegistrationController`, `ApiExceptionHandler`
  - 既存: コントローラ正常系と一部 API 結合テスト
  - 追加: 例外系（ヘッダー不足、NotFound、Forbidden）をコントローラ単体テストで補強

- アプリケーション層
  - 対象: `TasksApplicationService`, `UserRegistrationService`
  - 既存: 主要ユースケースの正常系・一部異常系
  - 追加: 入力正規化（title trim、completed の既定値）と境界値（taskId）検証

- インフラストラクチャ層
  - 対象: `JdbcTaskRepository`
  - 既存: API 結合テスト経由で間接検証のみ
  - 追加: `TaskMapper` モックを使った直接ユニットテスト（create/update/delete と例外分岐）

### テスト種別の分類

- 単体テスト
  - `src/test/java/com/todomanager/application/*Test.java`
  - `src/test/java/com/todomanager/presentation/*ControllerTest.java`
  - `src/test/java/com/todomanager/infrastructure/JdbcTaskRepositoryTest.java`

- 結合テスト（API エンドポイント起点）
  - `src/test/java/com/todomanager/presentation/TasksCrudApiIntegrationTest.java`
  - `src/test/java/com/todomanager/presentation/TasksApiSeededIntegrationTest.java`
  - `src/test/java/com/todomanager/presentation/TasksApiEmptySeedIntegrationTest.java`

### 重複と欠落の整理

- 重複整理
  - シードデータ有無の確認をサービス直接呼び出しから API エンドポイント経由へ統一
  - 統合テスト環境設定を `integrationtest` プロファイルへ集約

- 欠落補完
  - プレゼンテーション層の例外ハンドリング観点
  - アプリケーション層の入力正規化観点
  - インフラ層の永続化分岐（所有権違反・未存在）観点

### 実行環境の統一

- 追加: `src/test/resources/application-integrationtest.properties`
- 目的: ローカルで同条件の統合テストを再現するための datasource/sql 初期化設定の固定化
- Gradle: test ログ詳細化と JaCoCo レポート出力を有効化
