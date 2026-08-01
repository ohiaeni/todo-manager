# Todo Managerアプリ

このアプリケーションは、ユーザーがタスクを管理するためのシンプルなTodo Managerアプリです。
期限の長短は問わず、タスクを追加、編集、削除することができます。
登録したタスクは、LINE通知を通じてリマインドされます。

## 開発環境のダミーデータ

開発環境では、起動時に SQL 初期化を使ってダミーデータを投入できます。

- スキーマ定義: [todomanager/src/main/resources/schema.sql](todomanager/src/main/resources/schema.sql)
- ダミーデータ: [todomanager/src/main/resources/data.sql](todomanager/src/main/resources/data.sql)

起動すると `tasks` テーブルに既定の TODO レコードが投入され、`/api/v1/tasks` で確認できます。

### ダミーデータを空にして検証する方法

- テストでは [todomanager/src/test/resources/data-empty.sql](todomanager/src/test/resources/data-empty.sql) を使い、空データ状態を検証します。
- ローカルで空状態を確認したい場合は、`data.sql` の INSERT を外すか `DELETE FROM tasks;` のみ残して起動してください。
