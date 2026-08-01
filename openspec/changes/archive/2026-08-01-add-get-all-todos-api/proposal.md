## Why

Current task APIs do not provide a single endpoint to retrieve all TODO items, which makes basic list views and integrations harder to implement. Adding a dedicated list API now enables clients to load and display the full TODO collection with a stable contract.

## What Changes

- Add a new API endpoint to fetch all TODO items.
- Define the response behavior for empty and non-empty TODO collections.
- Ensure application and presentation layers expose a consistent list retrieval flow.
- Add or update tests for successful retrieval and response shape.

## Capabilities

### New Capabilities
- `todo-listing-api`: Provide a REST API contract for retrieving all TODO items.

### Modified Capabilities
- None.

## Impact

- Affected API surface: task-related HTTP endpoints.
- Affected code areas: presentation controller, application service, and related domain retrieval logic.
- Affected tests: controller/service tests for list retrieval behavior and payload structure.
