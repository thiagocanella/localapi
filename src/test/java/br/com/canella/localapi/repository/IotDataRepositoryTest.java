package br.com.canella.localapi.repository;

import br.com.canella.localapi.entity.IotData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IotDataRepositoryTest {

    @Autowired
    private IotDataRepository repository;

    @Test
    void testFindByDataName() {
        IotData data = new IotData(null, "Temperature", "22.5°C", LocalDateTime.now());
        repository.saveAndFlush(data);

        List<IotData> result = repository.findByDataName("Temperature");
        assertEquals(1, result.size());
        assertEquals("22.5°C", result.get(0).getDataRegistry());
    }

    @Test
    void testFindByCreatedAtBetween() {
        LocalDateTime now = LocalDateTime.now();
        IotData data = new IotData(null, "Humidity", "55%", now);
        repository.saveAndFlush(data);

        List<IotData> result = repository.findByCreatedAtBetween(now.minusMinutes(1), now.plusMinutes(1));
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindByDataRegistryContaining() {
        IotData data = new IotData(null, "Pressure", "1013 hPa", LocalDateTime.now());
        repository.saveAndFlush(data);

        List<IotData> result = repository.findByDataRegistryContaining("1013");
        assertEquals(1, result.size());
        assertEquals("Pressure", result.get(0).getDataName());
    }


}