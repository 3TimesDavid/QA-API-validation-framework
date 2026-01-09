package com.qa.framework.sutapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.framework.sutapi.domain.Status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestRecordIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Test
    void create_get_patch_get_flow_works() throws Exception {
        // 1) POST
        CreateTestRecordRequest create = new CreateTestRecordRequest();
        create.setName("Leakage current test - Luminaire A");
        create.setStatus(Status.DRAFT);

        String postResponse = mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.status").value("DRAFT"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extract id (simple parse)
        String id = objectMapper.readTree(postResponse).get("id").asText();
        assertThat(id).isNotBlank();

        // 2) GET
        mockMvc.perform(get("/test-records/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("DRAFT"));

        // 3) PATCH
        UpdateStatusRequest update = new UpdateStatusRequest();
        update.setStatus(Status.IN_PROGRESS);

        mockMvc.perform(patch("/test-records/{id}/status", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));

        // 4) GET again
        mockMvc.perform(get("/test-records/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }
}
