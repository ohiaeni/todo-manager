## 1. 現状確認と設定方針の固定

- [x] 1.1 `config/checkstyle` と `config/spotbugs` の既存 XML を点検し、現行コード構成と不整合な参照箇所を一覧化する
- [x] 1.2 `.vscode/settings.json` と `build.gradle` の現状連携を確認し、追加が必要な静的解析設定を確定する

## 2. Gradle への静的解析統合

- [x] 2.1 `build.gradle` に Checkstyle/SpotBugs のプラグインと依存設定を追加する
- [x] 2.2 Checkstyle/SpotBugs が `config` 配下を参照するよう設定し、`check` フローに組み込む
- [x] 2.3 解析結果レポートの出力先を確認し、開発者が確認しやすい形に整える

## 3. VS Code と設定ファイルの整合化

- [x] 3.1 `.vscode/settings.json` の静的解析参照設定を `config` 配下に統一する
- [x] 3.2 `config/checkstyle/suppressions.xml` の誤ったパッケージ参照や無効パスを修正する
- [x] 3.3 `config/spotbugs/exclude-filter.xml` の内容を見直し、必要な除外条件または構文修正を反映する

## 4. 検証とドキュメント更新

- [x] 4.1 `./gradlew check`（または個別解析タスク）を実行し、静的解析設定が有効に適用されることを確認する
- [x] 4.2 README に静的解析の実行手順と確認ポイントを追記する
- [x] 4.3 変更後の警告・抑制結果を確認し、意図しない誤抑制がないことを記録する
