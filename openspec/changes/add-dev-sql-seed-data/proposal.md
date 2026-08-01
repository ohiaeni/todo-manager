## Why

The project currently lacks a repeatable SQL-based setup for local development data, making verification of existing features dependent on ad-hoc manual input. Adding standard schema.sql and data.sql support now will make development and testing more predictable across team members.

## What Changes

- Add support for SQL bootstrap files used by the local development environment.
- Define requirements for schema creation and development-only dummy data provisioning.
- Ensure dummy data covers already implemented TODO-related features so existing endpoints can be exercised immediately after startup.
- Define expected behavior when seed data is present and when data is reset.

## Capabilities

### New Capabilities
- `dev-sql-seed-bootstrap`: Provide a stable behavior contract for initializing schema and dummy data through schema.sql and data.sql in development setup.

### Modified Capabilities
- None.

## Impact

- Affected systems: application startup data initialization path for local environments.
- Affected code/config: SQL bootstrap resources, data source initialization settings, and related startup conventions.
- Affected usage: developers can run the app with pre-seeded records that support existing TODO listing behavior checks.
