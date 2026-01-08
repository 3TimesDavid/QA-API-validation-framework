package com.qa.framework.sutapi.api;

import com.qa.framework.sutapi.domain.TestRecord;
import com.qa.framework.sutapi.repository.TestRecordRepository;

import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-records")
public class TestRecordController {

    private final TestRecordRepository repository;

    public TestRecordController(TestRecordRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestRecord create(@Valid @RequestBody CreateTestRecordRequest request) {
        TestRecord record = new TestRecord(request.getName(), request.getStatus());
        return repository.save(record);
    }

    @GetMapping("/{id}")
    public TestRecord getById(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("TestRecord not found: " + id));
    }

    @PatchMapping("/{id}/status")
    public TestRecord updateStatus(@PathVariable UUID id, @Valid @RequestBody UpdateStatusRequest request) {
        TestRecord record = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("TestRecord not found: " + id));
            record.setStatus(request.getStatus());
        return repository.save(record);
    }

}
