## Purpose

MyBatis を利用した永続化アクセスの構成を明確にし、単テーブルアクセスと複数テーブルアクセスで適切な実装パターンを使えるようにする。

## ADDED Requirements

### Requirement: 単テーブルアクセスには MyBatis Generator を利用する
システムは、単テーブルの CRUD 取得処理に対して MyBatis Generator で生成されたコードを SHALL 利用する。

#### Scenario: 単テーブルアクセスを実装する
- **WHEN** リポジトリが 1 テーブルの単純な取得・保存・更新・削除を扱う
- **THEN** その実装は MyBatis Generator 生成コードを通じて行われる

### Requirement: 複数テーブルアクセスには MyBatis XML を利用する
システムは、複数テーブルをまたぐ検索・集計・結合処理について、MyBatis の XML に SQL を記述した実装を SHALL 利用する。

#### Scenario: 複数テーブルにまたがるクエリを実装する
- **WHEN** リポジトリが複数テーブルを結合または集計して取得する
- **THEN** その実装は MyBatis XML で定義された SQL を利用する

### Requirement: OR マッパー実装は MyBatis を使用する
システムは、OR マッパー実装に対して MyBatis ベースの実装方式を MUST 採用する。

#### Scenario: OR マッパーを導入する
- **WHEN** 永続化レイヤーで OR マッパーを実装する
- **THEN** MyBatis を利用したマッパー構成となる

### Requirement: MyBatis 実装は既存アプリケーション挙動を維持する
システムは、MyBatis への移行後も既存のアプリケーション動作に影響がないことを MUST 維持する。

#### Scenario: 既存機能の動作を確認する
- **WHEN** MyBatis 実装へ移行した後に既存機能を実行する
- **THEN** 既存の入力・出力・エラー応答が維持される