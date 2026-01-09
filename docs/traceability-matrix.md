# Requirements Traceability Matrix

| Requirement ID | Description                           | Test Case ID | Automated Test Method                   |
|----------------|---------------------------------------|--------------|-----------------------------------------|
| FR-01          | API health check                      | TC-01        | health_returns_up                       |
| FR-02          | Create TestRecord                     | TC-02        | create_get_patch_get_flow_works         |
| FR-03          | Retrieve TestRecord by id             | TC-03        | create_get_patch_get_flow_works         |
| FR-04          | Update TestRecord status              | TC-04        | create_get_patch_get_flow_works         |
| FR-05          | Validate required fields on create    | TC-05        | create_without_name_returns_400         |
| FR-06          | Validate status values                | TC-06        | create_with_invalid_status_returns_400  |
| FR-07          | Handle non-existing TestRecord        | TC-07        | get_non_existing_id_returns_404         |
| FR-08          | Handle malformed identifiers          | TC-08        | patch_malformed_id_returns_400          |
