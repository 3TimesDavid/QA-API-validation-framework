package com.qa.framework.sutapi.api;

import com.qa.framework.sutapi.domain.Status;
import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {

    @NotNull
    private Status status;

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
