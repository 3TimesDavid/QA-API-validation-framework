# Test Plan

## 1. Objective
Validate the REST API against the defined requirements and provide evidence of quality through documentation, manual tests, and automated tests.

## 2. Scope
### In scope
- Health endpoint validation
- CRUD operations for test records
- Input validation and error handling
- Basic filtering for list endpoint
- Automated integration tests for core flows

### Out of scope (initial phase)
- Load/stress testing
- Security penetration testing
- UI testing (no frontend in this project)

## 3. Test Approach
- Requirements-based testing
- Risk-based prioritization (focus on core flows and error handling)
- Combination of:
  - Manual testing (Postman)
  - Automated integration tests (JUnit)

## 4. Test Types
- Functional testing
- Negative testing (invalid inputs)
- Integration testing (API + persistence)
- Basic regression testing (rerun core suite after changes)

## 5. Entry / Exit Criteria
### Entry criteria
- Requirements documented
- Endpoints available locally (or mocked if backend not ready)

### Exit criteria
- All high-priority test cases executed
- No open critical defects
- Traceability matrix updated

## 6. Deliverables
- Requirements document
- Test plan
- Test cases
- Traceability matrix
- Postman collection
- Automated test suite

## 7. Risks and Mitigations
- Risk: unclear requirements → Mitigation: refine FR/NFR iteratively
- Risk: API changes break tests → Mitigation: keep contract documented and update traceability
