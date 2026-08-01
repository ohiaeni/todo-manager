## Context

The project currently uses a JDBC-based repository implementation, and the repository layer is the most direct place to introduce MyBatis without changing the application's externally visible behavior. The design should preserve the existing domain model and API semantics while standardizing mapper usage for both simple and complex persistence operations.

## Goals / Non-Goals

**Goals:**
- Introduce MyBatis-based mapper implementation for the repository layer.
- Use MyBatis Generator for simple single-table access.
- Use MyBatis XML SQL for complex multi-table access.
- Keep the existing application behavior intact during the migration.

**Non-Goals:**
- Rewriting the domain model or API surface.
- Introducing a full ORM replacement beyond the mapper layer.
- Changing the runtime behavior of existing features beyond persistence implementation.

## Decisions

1. Use MyBatis as the persistence mapper framework.
- Rationale: It fits the requirement for XML-based SQL and a clear separation between SQL and Java code.
- Alternatives considered:
  - Keep JDBC only: rejected because it does not satisfy the new mapper convention.
  - Use JPA: rejected because the change explicitly requires MyBatis and XML SQL style.

2. Use MyBatis Generator for single-table access.
- Rationale: Generated code is appropriate for standard CRUD operations and reduces boilerplate.
- Alternatives considered:
  - Hand-write all mapper methods: rejected because it increases maintenance effort.

3. Use custom MyBatis XML mapping for multi-table access.
- Rationale: Complex joins and aggregations are easier to express clearly in XML SQL.
- Alternatives considered:
  - Put all SQL in Java annotations: rejected because the requirement specifically asks for XML-based SQL.

4. Keep the repository boundary stable.
- Rationale: Existing application service and controller code should not need to change.
- Alternatives considered:
  - Rework the service layer to depend on distinct interfaces: rejected because it increases the migration surface.

## Risks / Trade-offs

- [Risk] Generated code and handwritten mapper XML may diverge in conventions.
  - Mitigation: Keep a small set of documented mapper patterns and place generated artifacts under a clearly separated package.

- [Risk] Migration may temporarily introduce duplicated query logic.
  - Mitigation: Move only the most stable repository methods first and keep the current behavior as the compatibility target.

- [Trade-off] XML-based SQL improves clarity for complex queries but increases the amount of SQL text that must be reviewed.
  - Mitigation: Keep SQL focused, use comments sparingly, and validate behavior through tests.

## Migration Plan

1. Introduce MyBatis dependencies and configuration for the repository layer.
2. Add MyBatis Generator setup and generated mapper/model artifacts for single-table access.
3. Create MyBatis XML mapper definitions for multi-table access paths.
4. Replace JDBC repository implementations with MyBatis-backed implementations while preserving contracts.
5. Validate behavior with existing tests and add focused regression tests for mapper behavior.

## Open Questions

- Whether the project should generate mapper code for all current entities immediately or only for the currently used tables first.
- Whether the repository layer should keep the existing JDBC-based implementation as a fallback during the migration window.