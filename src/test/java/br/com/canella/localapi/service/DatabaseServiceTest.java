package br.com.canella.localapi.service;

import br.com.canella.localapi.entity.IotData;
import br.com.canella.localapi.repository.IotDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseServiceTest {

    @Mock
    private IotDataRepository iotDataRepository;

    @InjectMocks
    private DatabaseService databaseService;

    private IotData sampleData;

    @BeforeEach
    void setUp() {
        sampleData = new IotData(1L, "Temperature", "22.5°C", LocalDateTime.now());
    }

    @Test
    void testSave() {
        when(iotDataRepository.save(sampleData)).thenReturn(sampleData);
        IotData result = databaseService.save(sampleData);
        assertNotNull(result);
        assertEquals("Temperature", result.getDataName());
        verify(iotDataRepository).save(sampleData);
    }

    @Test
    void testFindById() {
        when(iotDataRepository.findById(1L)).thenReturn(Optional.of(sampleData));
        IotData result = databaseService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(iotDataRepository).findById(1L);
    }

    @Test
    void testFindAll() {
        when(iotDataRepository.findAll()).thenReturn(List.of(sampleData));
        List<IotData> result = databaseService.findAll();
        assertEquals(1, result.size());
        verify(iotDataRepository).findAll();
    }

    @Test
    void testFindByDataName() {
        when(iotDataRepository.findByDataName("Temperature")).thenReturn(List.of(sampleData));
        List<IotData> result = databaseService.findByDataName("Temperature");
        assertFalse(result.isEmpty());
        verify(iotDataRepository).findByDataName("Temperature");
    }

    @Test
    void testFindByCreatedAtBetween() {
        LocalDateTime start = sampleData.getCreatedAt().minusHours(1);
        LocalDateTime end = sampleData.getCreatedAt().plusHours(1);
        when(iotDataRepository.findByCreatedAtBetween(start, end)).thenReturn(List.of(sampleData));
        List<IotData> result = databaseService.findByCreatedAtBetween(start, end);
        assertEquals(1, result.size());
        verify(iotDataRepository).findByCreatedAtBetween(start, end);
    }

    @Test
    void testFindByDataRegistryContaining() {
        when(iotDataRepository.findByDataRegistryContaining("22")).thenReturn(List.of(sampleData));
        List<IotData> result = databaseService.findByDataRegistryContaining("22");
        assertFalse(result.isEmpty());
        verify(iotDataRepository).findByDataRegistryContaining("22");
    }

    @Test
    void testSearchByParams() {
        when(iotDataRepository.searchByParams("Temperature", "22", sampleData.getCreatedAt()))
            .thenReturn(List.of(sampleData));
        List<IotData> result = databaseService.searchByParams("Temperature", "22", sampleData.getCreatedAt());
        assertEquals(1, result.size());
        verify(iotDataRepository).searchByParams("Temperature", "22", sampleData.getCreatedAt());
    }

    @Test
    void testDeleteById() {
        doNothing().when(iotDataRepository).deleteById(1L);
        databaseService.deleteById(1L);
        verify(iotDataRepository).deleteById(1L);
    }
}