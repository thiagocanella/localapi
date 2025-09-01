package br.com.canella.localapi.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.canella.localapi.entity.IotData;

public interface PersistenceService {
    
    IotData save(IotData iotData);

    IotData findById(Long id);

    List<IotData> findAll();

    List<IotData> findByDataName(String dataName);

    List<IotData> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<IotData> findByDataRegistryContaining(String keyword);

    List<IotData> searchByParams(String dataName, String dataRegistry, LocalDateTime createdAt);

    void deleteById(Long id);

}
