package com.qa.framework.sutapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.framework.sutapi.domain.Status;

import org.junit.jupiter.api.Tag;
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

    // ---------- Helpers ----------
    private String createRecordAndReturnId(String name, Status status) throws Exception {
        CreateTestRecordRequest create = new CreateTestRecordRequest();
        create.setName(name);
        create.setStatus(status);

        String body = mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = objectMapper.readTree(body).get("id").asText();
        assertThat(id).isNotBlank();
        return id;
    }

    // ---------- Smoke ----------
    @Tag("smoke")
    @Test
    void health_returns_up() throws Exception {
        mockMvc.perform(get("/health")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    // ---------- Happy path ----------
    @Tag("happy")
    @Test
    void create_get_patch_get_flow_works() throws Exception {
        String id = createRecordAndReturnId("Leakage current test - Luminaire A", Status.DRAFT);

        mockMvc.perform(get("/test-records/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("DRAFT"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.updatedAt").exists());

        UpdateStatusRequest update = new UpdateStatusRequest();
        update.setStatus(Status.IN_PROGRESS);

        mockMvc.perform(patch("/test-records/{id}/status", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));

        mockMvc.perform(get("/test-records/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    // ---------- POST negative ----------
    @Tag("negative")
    @Test
    void create_without_name_returns_400() throws Exception {
        String json = """
            { "status": "DRAFT" }
            """;

        mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Tag("negative")
    @Test
    void create_with_short_name_returns_400() throws Exception {
        String json = """
            { "name": "ab", "status": "DRAFT" }
            """;

        mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Tag("negative")
    @Test
    void create_with_long_name_returns_400() throws Exception {
        String longName = "a".repeat(101);
        String json = """
            { "name": "%s", "status": "DRAFT" }
            """.formatted(longName);

        mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Tag("negative")
    @Test
    void create_without_status_returns_400() throws Exception {
        String json = """
            { "name": "Leakage current test - Luminaire A" }
            """;

        mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Tag("negative")
    @Test
    void create_with_invalid_status_returns_400() throws Exception {
        String json = """
            { "name": "Leakage current test - Luminaire A", "status": "UNKNOWN" }
            """;

        mockMvc.perform(post("/test-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    // ---------- GET negative ----------
    @Tag("negative")
    @Test
    void get_non_existing_id_returns_404() throws Exception {
        String nonExistingId = "00000000-0000-0000-0000-000000000000";

        mockMvc.perform(get("/test-records/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Tag("negative")
    @Test
    void get_malformed_id_returns_400() throws Exception {
        mockMvc.perform(get("/test-records/{id}", "not-a-uuid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // ---------- PATCH negative ----------
    @Tag("negative")
    @Test
    void patch_without_status_returns_400() throws Exception {
        String id = createRecordAndReturnId("Leakage current test - Luminaire A", Status.DRAFT);

        String json = """
            { }
            """;

        mockMvc.perform(patch("/test-records/{id}/status", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Tag("negative")
    @Test
    void patch_invalid_status_returns_400() throws Exception {
        String id = createRecordAndReturnId("Leakage current test - Luminaire A", Status.DRAFT);

        String json = """
            { "status": "UNKNOWN" }
            """;

        mockMvc.perform(patch("/test-records/{id}/status", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Tag("negative")
    @Test
    void patch_non_existing_id_returns_404() throws Exception {
        String nonExistingId = "00000000-0000-0000-0000-000000000000";

        String json = """
            { "status": "IN_PROGRESS" }
            """;

        mockMvc.perform(patch("/test-records/{id}/status", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Tag("negative")
    @Test
    void patch_malformed_id_returns_400() throws Exception {
        String json = """
            { "status": "IN_PROGRESS" }
            """;

        mockMvc.perform(patch("/test-records/{id}/status", "not-a-uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
