# Test Cases – TestRecord API

## TC-01 – Health check returns UP
- **Endpoint:** GET /health
- **Description:** Verify that the API is running and reachable.
- **Expected result:** HTTP 200 and body { "status": "UP" }
- **Type:** Smoke
- **Automation:** Yes
- **Automated test:** health_returns_up

---

## TC-02 – Create TestRecord (valid request)
- **Endpoint:** POST /test-records
- **Description:** Create a new TestRecord with valid name and status.
- **Expected result:** HTTP 201 and generated id, createdAt, updatedAt.
- **Type:** Happy path
- **Automation:** Yes
- **Automated test:** create_get_patch_get_flow_works

---

## TC-03 – Get TestRecord by id (existing)
- **Endpoint:** GET /test-records/{id}
- **Description:** Retrieve an existing TestRecord by id.
- **Expected result:** HTTP 200 and correct TestRecord data.
- **Type:** Happy path
- **Automation:** Yes
- **Automated test:** create_get_patch_get_flow_works

---

## TC-04 – Update TestRecord status
- **Endpoint:** PATCH /test-records/{id}/status
- **Description:** Update the status of an existing TestRecord.
- **Expected result:** HTTP 200 and updated status.
- **Type:** Happy path
- **Automation:** Yes
- **Automated test:** create_get_patch_get_flow_works

---

## TC-05 – Create TestRecord without name
- **Endpoint:** POST /test-records
- **Description:** Try to create a TestRecord without name.
- **Expected result:** HTTP 400 Bad Request.
- **Type:** Negative
- **Automation:** Yes
- **Automated test:** create_without_name_returns_400

---

## TC-06 – Create TestRecord with invalid status
- **Endpoint:** POST /test-records
- **Description:** Try to create a TestRecord with invalid status value.
- **Expected result:** HTTP 400 Bad Request.
- **Type:** Negative
- **Automation:** Yes
- **Automated test:** create_with_invalid_status_returns_400

---

## TC-07 – Get TestRecord with non-existing id
- **Endpoint:** GET /test-records/{id}
- **Description:** Retrieve a TestRecord with an id that does not exist.
- **Expected result:** HTTP 404 Not Found.
- **Type:** Negative
- **Automation:** Yes
- **Automated test:** get_non_existing_id_returns_404

---

## TC-08 – Update TestRecord with malformed id
- **Endpoint:** PATCH /test-records/{id}/status
- **Description:** Update status using a malformed id.
- **Expected result:** HTTP 400 Bad Request.
- **Type:** Negative
- **Automation:** Yes
- **Automated test:** patch_malformed_id_returns_400
