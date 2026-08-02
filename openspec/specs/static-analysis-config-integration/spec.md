# static-analysis-config-integration Specification

## Purpose
`config` 配下の静的解析設定をビルドとエディタに一貫して適用し、ローカル開発時と CI 相当の品質チェック結果を再現可能にするための契約を定義する。

## Requirements

### Requirement: Gradle は静的解析設定を明示的に参照して実行する
システムは、Gradle 実行時に Checkstyle と SpotBugs の設定ファイルを `config` 配下から SHALL 読み込み、解析タスクとして実行できる状態を提供する。

#### Scenario: 開発者が静的解析タスクを実行する
- **WHEN** 開発者が Gradle で静的解析を実行する
- **THEN** Checkstyle と SpotBugs が `config` 配下の設定を参照して結果を出力する

### Requirement: VS Code は同一の Checkstyle 設定を参照する
システムは、VS Code の Java 開発環境で Checkstyle 設定を SHALL `config/checkstyle` 配下に統一して参照する。

#### Scenario: 開発者が VS Code で Java ファイルを編集する
- **WHEN** Checkstyle がエディタ上で解析を行う
- **THEN** Gradle 実行時と同じ Checkstyle ルールセットで診断が表示される

### Requirement: 設定ファイルの参照先は現行パッケージ構成と整合する
システムは、`config` 配下の静的解析設定に含まれる除外・抑制ルールの参照先を MUST 現在のプロジェクト構成と整合させる。

#### Scenario: 解析ルールの対象/除外を評価する
- **WHEN** 開発者が静的解析を実行する
- **THEN** 意図しないパッケージ名や無効なパス指定による誤抑制が発生しない
