# Test Cases

## Conventions
- IDs: TC-001, TC-002...
- Each test case maps to one or more requirements (FR/NFR).

---

### TC-001 – Health endpoint returns OK
**Related requirements:** FR-01  
**Preconditions:** API running  
**Steps:**
1. Send GET request to `/health`
**Expected result:**
- HTTP 200
- Response indicates service is healthy

---

### TC-002 – Create test record with valid payload
**Related requirements:** FR-02, NFR-01  
**Preconditions:** API running  
**Steps:**
1. Send POST request to `/test-records` with a valid JSON body
**Expected result:**
- HTTP 201
- Response includes created record with an identifier and status

---

### TC-003 – Create test record with invalid payload is rejected
**Related requirements:** NFR-01, NFR-02  
**Preconditions:** API running  
**Steps:**
1. Send POST request to `/test-records` with missing required fields
**Expected result:**
- HTTP 400
- Error response format is consistent and includes a readable message
