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

---

### TC-006 – Retrieve existing test record by id
**Related requirements:** FR-03  
**Preconditions:** API running and a test record exists  
**Steps:**
1. Create a test record via POST `/test-records` (TC-002)
2. Send GET request to `/test-records/{id}` using the returned `id`
**Expected result:**
- HTTP 200 OK
- Response contains the same `id` created in step 1

---

### TC-007 – Update test record status and verify persistence
**Related requirements:** FR-04  
**Preconditions:** API running and a test record exists  
**Steps:**
1. Create a test record via POST `/test-records` (TC-002)
2. Send PATCH request to `/test-records/{id}/status` with:
```json
    { "status": "IN_PROGRESS" }
```
**Expected result:**
- PATCH request returns HTTP 200 OK
- GET request returns HTTP 200 OK
- The returned test record has `status = IN_PROGRESS`
