package com.qa.framework.sutapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.framework.sutapi.domain.TestRecord;

public interface TestRecordRepository extends JpaRepository<TestRecord, UUID> {
}