# Todo Managerアプリ

このアプリケーションは、ユーザーがタスクを管理するためのシンプルなTodo Managerアプリです。
期限の長短は問わず、タスクを追加、編集、削除することができます。
登録したタスクは、LINE通知を通じてリマインドされます。

## OpenSpec 文書の作成ルール

今後の OpenSpec の提案・仕様・設計・タスク文書は、日本語で作成するものとします。
また、文書の構造（Purpose、Requirement、Scenario、見出し階層）は維持し、レビュー時には訳抜け・意味変化・用語不一致を確認します。

## 開発環境のダミーデータ

開発環境では、起動時に SQL 初期化を使ってダミーデータを投入できます。

- スキーマ定義: [todomanager/src/main/resources/schema.sql](todomanager/src/main/resources/schema.sql)
- ダミーデータ: [todomanager/src/main/resources/data.sql](todomanager/src/main/resources/data.sql)

起動すると `tasks` テーブルに既定の TODO レコードが投入され、`/api/v1/tasks` で確認できます。

### ダミーデータを空にして検証する方法

- テストでは [todomanager/src/test/resources/data-empty.sql](todomanager/src/test/resources/data-empty.sql) を使い、空データ状態を検証します。
- ローカルで空状態を確認したい場合は、`data.sql` の INSERT を外すか `DELETE FROM tasks;` のみ残して起動してください。

## テスト実行の標準手順

本リポジトリでは、単体テストと結合テストを同じコマンドで実行できます。

- 実行コマンド: `cd todomanager && ./gradlew test`
- レポート:
	- テスト結果: `todomanager/build/reports/tests/test/index.html`
	- カバレッジ: `todomanager/build/reports/jacoco/test/html/index.html`

### テスト用プロファイル

- 通常の統合テスト: `integrationtest` プロファイル（`src/test/resources/application-integrationtest.properties`）
- 空シード検証: `empty-seed` プロファイル（`src/test/resources/application-empty-seed.properties`）

これにより、ローカル環境でも追加の手作業設定なしで API 起点の結合テストを再現できます。

## 静的解析の標準手順

`config` 配下の設定を使って、Gradle と VS Code の両方で同じ基準の静的解析を行えます。

- 実行コマンド: `cd todomanager && ./gradlew check`
- 主なレポート出力先:
	- Checkstyle（main）: `todomanager/build/reports/checkstyle/main.html`
	- Checkstyle（test）: `todomanager/build/reports/checkstyle/test.html`
	- SpotBugs（main）: `todomanager/build/reports/spotbugs/main.html`
	- SpotBugs（test）: `todomanager/build/reports/spotbugs/test.html`

### 補足

- VS Code は `config/checkstyle/checkstyle.xml` を参照します。
- SpotBugs は Java 25 環境でも `./gradlew check` の実行フローで有効化されています。
