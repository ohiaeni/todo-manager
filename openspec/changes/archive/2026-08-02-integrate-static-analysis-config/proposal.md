## なぜ必要か

`config` 配下に静的解析ツール用の XML を追加しても、Gradle と VS Code がその設定を参照しなければ開発フローに反映されない。加えて既存の設定ファイルに実プロジェクトと不整合な記述があり、解析結果の信頼性を下げるため、設定統合と整合性修正を同時に行う必要がある。

## 何を変更するか

- Gradle ビルドに Checkstyle / SpotBugs の設定読み込みと実行タスク連携を追加する。
- VS Code 側で Java 向け静的解析設定を `config` 配下のファイルに統一して参照できるようにする。
- `config/checkstyle/suppressions.xml` や SpotBugs フィルタなど、既存設定の不整合や誤記を修正する。
- 開発者がローカルで同一手順で解析を実行できるよう、実行方法をドキュメントに反映する。

## ケイパビリティ

### 新規ケイパビリティ

- `static-analysis-config-integration`: 静的解析設定をビルドとエディタに統合し、設定ファイルの整合性を保証する。

### 変更する既存ケイパビリティ

- なし

## 影響範囲

- `todomanager/build.gradle` のプラグイン・タスク定義
- `.vscode/settings.json` の Java 関連設定
- `config/checkstyle/*.xml` と `config/spotbugs/*.xml`
- 静的解析手順を記載するドキュメント（README 等）
