package br.com.canella.localapi.controller;

import br.com.canella.localapi.entity.IotData;
import br.com.canella.localapi.service.PersistenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IotController.class)
class IotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersistenceService persistenceService;

    @Autowired
    private ObjectMapper objectMapper;

    private final IotData sampleData = new IotData(1L, "Temperature", "22.5°C", LocalDateTime.now());

    @Test
    void testSave() throws Exception {
        Mockito.when(persistenceService.save(any())).thenReturn(sampleData);

        mockMvc.perform(post("/iot-data-logger")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleData.getId()))
                .andExpect(jsonPath("$.dataName").value("Temperature"));
    }

    @Test
    void testFindById() throws Exception {
        Mockito.when(persistenceService.findById(1L)).thenReturn(sampleData);

        mockMvc.perform(get("/iot-data-logger/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dataName").value("Temperature"));
    }

    @Test
    void testFindAll() throws Exception {
        Mockito.when(persistenceService.findAll()).thenReturn(List.of(sampleData));

        mockMvc.perform(get("/iot-data-logger"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dataName").value("Temperature"));
    }

    @Test
    void testSearchByParams() throws Exception {
        Mockito.when(persistenceService.searchByParams(anyString(), anyString(), any())).thenReturn(List.of(sampleData));

        mockMvc.perform(get("/iot-data-logger/search")
                .param("dataName", "Temperature")
                .param("dataRegistry", "22")
                .param("createdAt", sampleData.getCreatedAt().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dataName").value("Temperature"));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/iot-data-logger/1"))
                .andExpect(status().isOk());

        Mockito.verify(persistenceService).deleteById(1L);
    }
}