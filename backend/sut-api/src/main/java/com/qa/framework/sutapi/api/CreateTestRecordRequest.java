package com.qa.framework.sutapi.api;

import com.qa.framework.sutapi.domain.Status;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTestRecordRequest {

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private Status status;

    public String getName() { return name; }
    public Status getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setStatus(Status status) { this.status = status; }
}
