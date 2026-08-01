## Context

Current task endpoints do not expose a dedicated list-all operation. See proposal.md (Why) for motivation. The codebase is a layered Spring Boot application with presentation and application layers already in place, so this change should reuse existing patterns and avoid new dependencies.

## Goals / Non-Goals

**Goals:**
- Add a read-only HTTP endpoint that returns all TODO items.
- Keep retrieval flow consistent across controller and application service boundaries.
- Return stable list payloads for both non-empty and empty datasets.
- Validate behavior through controller/service level tests.

**Non-Goals:**
- No filtering, sorting, or pagination in this change.
- No data model redesign or persistence technology changes.
- No authentication or authorization model changes.

## Decisions

1. Expose a GET endpoint on the existing tasks API surface.
- Rationale: Matches REST conventions for collection reads and minimizes client learning cost.
- Alternatives considered:
  - Add a POST-based search endpoint: rejected because this change has no query criteria.
  - Reuse another existing endpoint with flags: rejected due to less clear API semantics.

2. Implement retrieval through the existing application service layer.
- Rationale: Preserves layering and keeps controller focused on request/response mapping.
- Alternatives considered:
  - Query domain/repository directly from controller: rejected because it bypasses application orchestration.

3. Represent zero TODO items as HTTP success with an empty list.
- Rationale: Empty collection is a valid state, not an error condition.
- Alternatives considered:
  - Return 404 when empty: rejected because the collection resource exists even when it has no members.

4. Keep response fields aligned with current TODO representation (identifier, title, completion status).
- Rationale: Meets the new spec while minimizing schema drift for clients.
- Alternatives considered:
  - Introduce a new response envelope/version: rejected as unnecessary for this scoped addition.

## Risks / Trade-offs

- [Risk] Existing tests may not cover list retrieval edge cases thoroughly.
  - Mitigation: Add tests for non-empty and empty collection scenarios with payload assertions.

- [Risk] Future requirements for filtering/pagination may require API evolution.
  - Mitigation: Keep endpoint contract simple now and add query parameters in a later backward-compatible change.

- [Trade-off] Returning all TODO items may become expensive as data grows.
  - Mitigation: Document pagination as a follow-up capability if volume constraints emerge.

## Migration Plan

1. Add application service method for retrieving all TODO items.
2. Add/extend controller endpoint wiring for GET collection request.
3. Add or update tests for success, empty response, and field structure.
4. Run test suite and verify no behavioral regression.

Rollback strategy:
- Revert endpoint and service additions if regression is found; this change is additive and isolated to read paths.
