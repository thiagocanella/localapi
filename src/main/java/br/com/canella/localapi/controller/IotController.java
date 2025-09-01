package br.com.canella.localapi.controller;

import br.com.canella.localapi.dto.IotDataRequest;
import br.com.canella.localapi.entity.IotData;
import br.com.canella.localapi.service.PersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/iot-data-logger")
public class IotController {

    private final PersistenceService persistenceService;

    @PostMapping
    public IotData save(@RequestBody IotDataRequest request) {
        IotData iotData = new IotData();
        iotData.setDataName(request.getDataName());
        iotData.setDataRegistry(request.getDataRegistry());
        iotData.setCreatedAt(LocalDateTime.now());
        log.info("Saving iot data {}", iotData.toString());

        return persistenceService.save(iotData);
    }

    @GetMapping("/{id}")
    public IotData findById(@PathVariable Long id) {
        log.info("Fetching IotData by ID: {}", id);
        return persistenceService.findById(id);
    }

    @GetMapping
    public List<IotData> findAll() {
        log.info("Fetching all IotData records");
        return persistenceService.findAll();
    }

    @GetMapping("/search")
    public List<IotData> searchByParams(
            @RequestParam(required = false) String dataName,
            @RequestParam(required = false) String dataRegistry,
            @RequestParam(required = false) LocalDateTime createdAt) {
        log.info("Searching IotData with params - dataName: {}, dataRegistry: {}, createdAt: {}",
                dataName, dataRegistry, createdAt);
        return persistenceService.searchByParams(dataName, dataRegistry, createdAt);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Deleting IotData by ID: {}", id);
        persistenceService.deleteById(id);
    }
}