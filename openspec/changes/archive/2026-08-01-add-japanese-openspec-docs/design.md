## Context

OpenSpec 文書は main spec と changes 配下の計画文書が増えており、現状は英語と日本語が混在している。proposal.md の Why で述べたとおり、対象はアプリ挙動ではなく文書運用である。仕様構造（Purpose、Requirement、Scenario）を維持したまま、日本語統一を継続運用できる仕組みが必要。

## Goals / Non-Goals

**Goals:**
- OpenSpec の新規文書を日本語で作成する運用基準を定義する。
- 既存の main spec と changes 文書を日本語へ統一する実施手順を確立する。
- 翻訳時に Requirement/Scenario 構造と意味を保持する検証方法を明確化する。
- 用語統一ルールを設け、文書間の表現ゆれを抑制する。

**Non-Goals:**
- アプリケーション機能や API 振る舞いの変更は行わない。
- OpenSpec スキーマ自体の拡張や CLI 仕様変更は行わない。
- 自動翻訳ツール導入の是非はこの変更では決定しない。

## Translation and Review Rules

- 新規の OpenSpec 文書は日本語で作成する。
- Purpose、Requirement、Scenario と見出し階層は構造として維持する。
- Requirement/Scenario などの OpenSpec スキーマ名は一貫して扱い、説明文は日本語に統一する。
- レビューでは、訳抜け・意味変化・用語不一致がないかを確認する。

## Decisions

1. 文書対象を main specs と changes（active/archived）に明示する。
- 理由: 「既存のものも日本語化」の範囲を曖昧にしないため。
- 代替案:
  - active changes のみ対象: 却下。過去資産との一貫性が残らない。

2. 変換は「構造維持優先」で実施する。
- 理由: 見出し階層や要件境界が崩れると、将来の sync/apply/archive の運用に影響するため。
- 代替案:
  - 自由翻訳で可読性優先: 却下。要件対応関係の追跡が困難になる。

3. 用語集とレビュー観点をタスク化して定着させる。
- 理由: 単発の翻訳ではなく、継続的に日本語品質を維持するため。
- 代替案:
  - 翻訳作業のみ実施: 却下。再発防止にならない。

4. 小さな単位で段階的に日本語化を進める。
- 理由: 大規模一括置換は意味ずれや見落としのリスクが高い。
- 代替案:
  - 一括変換: 却下。レビュー負荷と差分検証が過大。

## Risks / Trade-offs

- [Risk] 意味ずれが発生し、原要件と解釈が変わる。
  - Mitigation: Requirement 単位で対照レビューし、Scenario の WHEN/THEN 対応を確認する。

- [Risk] archived changes まで対象にすると作業量が増える。
  - Mitigation: 優先順位（main specs → active changes → archived changes）で段階的に実施する。

- [Trade-off] 日本語統一でチーム内可読性は上がる一方、外部英語資料との対比が減る。
  - Mitigation: 必要に応じて用語集に英語対訳を残し、検索性を維持する。

## Migration Plan

1. 日本語化対象ファイルの一覧を確定する（main specs、changes 配下）。
2. main specs を日本語化し、構造保持レビューを行う。
3. active changes の proposal/specs/design/tasks を日本語化する。
4. archived changes を優先度順に日本語化する。
5. 用語集・レビュー観点を文書化し、以後の作成運用に適用する。

Rollback strategy:
- 意味ずれが検出された場合は該当ファイルを翻訳前内容へ戻し、Requirement 単位で再翻訳する。
