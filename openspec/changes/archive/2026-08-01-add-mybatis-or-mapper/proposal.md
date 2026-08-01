## Why

The current Todo Manager persistence layer is using JDBC directly, which makes query logic harder to maintain and less expressive for multi-table access. Introducing MyBatis as the ORM/mapper layer will make repository code clearer, support XML-defined SQL, and establish a consistent convention for both simple single-table access and more complex multi-table queries.

## What Changes

- Introduce MyBatis as the persistence mapper approach for the repository layer.
- Use MyBatis XML SQL definitions for custom multi-table queries.
- Use MyBatis Generator-generated code for simple single-table access.
- Define the expected persistence behavior and migration approach for repository implementations.

## Capabilities

### New Capabilities
- `mybatis-mapper-integration`: Define the persistence contract and migration approach for adopting MyBatis in the repository layer.

### Modified Capabilities
- None.

## Impact

- Affected code: repository implementations, mapper interfaces, SQL mapping XML, and generated model/mapper code.
- Affected dependencies: persistence layer build and configuration for MyBatis support.
- Affected behavior: repository access becomes more structured, but the external application behavior remains the same.