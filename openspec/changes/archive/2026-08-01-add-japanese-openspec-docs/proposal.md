## Why

OpenSpec 文書に英語と日本語が混在しており、レビューや保守時の理解コストが高くなっています。以後の運用ルールを日本語に統一し、既存文書も含めて表記ゆれを解消することで、仕様運用の一貫性を高めます。

## What Changes

- OpenSpec で新規作成する提案・設計・タスク・仕様文書を日本語で記述する運用要件を追加する。
- 既存の OpenSpec 文書（main specs と active/archived changes 配下の計画文書）を日本語へ統一する。
- 文書変換時に、要件の意味・構造・見出しレベルを維持するルールを定義する。
- 変換後のレビュー観点（訳抜け・意味変化・用語統一）を明文化する。

## Capabilities

### New Capabilities
- `openspec-japanese-documentation`: OpenSpec 文書を日本語で一貫して管理するための記述・変換・検証ルールを定義する。

### Modified Capabilities
- None.

## Impact

- 影響対象: openspec/specs 配下の既存仕様、openspec/changes 配下の既存計画文書、および今後作成する OpenSpec 文書。
- 影響範囲: 文書運用プロセス、レビュー手順、用語統一ルール。
- API やアプリケーション実行挙動への直接的な影響はない。
