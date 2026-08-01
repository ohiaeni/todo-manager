## Purpose

OpenSpec 文書を日本語で統一管理し、既存文書の言語変換時にも要件の意味と構造を維持できるようにする。

## ADDED Requirements

### Requirement: 新規 OpenSpec 文書は日本語で記述する
システムは、新規作成される OpenSpec の計画文書および仕様文書を日本語で記述することを SHALL 満たす。

#### Scenario: 新規変更文書の作成
- **WHEN** 提案、仕様差分、設計、タスク文書を新規作成する
- **THEN** 文書本文は日本語で記述される

### Requirement: 既存 OpenSpec 文書を日本語へ統一する
システムは、既存の main spec と既存 change artifacts を含む OpenSpec 文書を日本語へ統一変換する手順を MUST 提供する。

#### Scenario: 既存文書の日本語化
- **WHEN** 既存の OpenSpec 文書を言語統一対象として処理する
- **THEN** 対象文書は日本語表記へ統一される

### Requirement: 翻訳時に要件構造を保持する
システムは、OpenSpec 文書を翻訳する際に Requirement 見出し、Scenario 構造、要件意図を SHALL 保持する。

#### Scenario: 要件構造の保持確認
- **WHEN** 英語文書を日本語へ変換する
- **THEN** Requirement と Scenario の見出し構造および要件意味は維持される

### Requirement: 用語統一とレビュー観点を定義する
システムは、誤訳・訳抜け・意味ドリフトを検出するための日本語用語集とレビュー観点を MUST 定義する。

#### Scenario: レビューでの確認
- **WHEN** 日本語化後の文書をレビューする
- **THEN** 訳抜け、意味変化、用語不一致の有無を確認できる

- 構造維持: Purpose、Requirement、Scenario、および見出し階層は翻訳前後で維持する
- 用語統一: Requirement/Scenario などの OpenSpec スキーマ名は一貫して扱い、説明文は日本語で統一する
- レビュー観点: 見出しレベル崩れ、訳抜け、意味変化、用語不一致を確認する
