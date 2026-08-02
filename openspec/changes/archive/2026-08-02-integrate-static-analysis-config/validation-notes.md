## 検証メモ

### 1.1 設定点検で確認した不整合

- `config/checkstyle/suppressions.xml` に旧プロジェクト由来の `com/dressca/...` パスが残っていた。
- `config/spotbugs/exclude-filter.xml` が空で、生成コード除外の意図が明示されていなかった。
- `.vscode/settings.json` の Checkstyle 参照先と Gradle 側の実行系で、参照ルール統一が不足していた。

### 4.3 修正後の確認

- `./gradlew check` が成功し、Checkstyle 設定が `config/checkstyle/checkstyle.xml` 経由で適用された。
- SpotBugs は設定を導入済みで、Java 25 実行環境でも `check` フローで実行される。
- `config/checkstyle/suppressions.xml` は現行パッケージ (`com/todomanager/generated/...`) に整合させた。
- `config/spotbugs/exclude-filter.xml` に生成コード除外ルールを追加し、意図しない誤抑制を避ける最小設定とした。
