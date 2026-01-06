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

### TC-003 – Create test record with missing required fields is rejected
**Related requirements:** NFR-01, NFR-02  
**Preconditions:** API running  
**Steps:**
1. Send POST request to `/test-records` with a JSON body missing required fields
**Expected result:**
- HTTP 400 Bad Request
- Error response indicates missing required fields
- Error response format is consistent and readable

---

### TC-004 – Create test record with too short `name`
**Related requirements:** FR-02, NFR-01  
**Preconditions:** API running  
**Steps:**
1. Send POST request to `/test-records` with body:
```json
{
    "name": "Hi",
    "status": "DRAFT"
}
```
**Expected result:**
- HTTP 400 Bad Request
- Validation error related to minimum length of `name`


---

### TC-005 – Create test record with invalid `status`
**Related requirements:** FR-02, NFR-01, NFR-02 
**Preconditions:** API running  
**Steps:**
1. Send POST request to `/test-records` with body:
```json
{
"name": "Leakage current test - Luminaire A",
"status": "INVALID"
}
```
**Expected result:**
- HTTP 400 Bad Request
- Error response indicates invalid status value
- Error response format follows the standard


