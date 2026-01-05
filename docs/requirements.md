# Requirements (SUT)

## Functional Requirements (FR)

**FR-01 – Health check**  
The API shall provide a health endpoint to confirm service availability.

**FR-02 – Create test record**  
The API shall allow creating a test record with a unique identifier and a status.

**FR-03 – Retrieve test record**  
The API shall allow retrieving a test record by its identifier.

**FR-04 – Update test status**  
The API shall allow updating the status of an existing test record.

**FR-05 – List test records**  
The API shall allow listing existing test records with basic filtering by status.

## Non-Functional Requirements (NFR)

**NFR-01 – Input validation**  
The API shall validate request payloads and reject invalid data with clear error messages.

**NFR-02 – Consistent error responses**  
The API shall return consistent error formats including HTTP status code and a human-readable message.

**NFR-03 – Basic performance**  
Common endpoints shall respond within an acceptable time under normal local development conditions.
