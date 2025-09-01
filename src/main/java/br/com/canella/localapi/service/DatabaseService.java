package br.com.canella.localapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.canella.localapi.entity.IotData;
import br.com.canella.localapi.repository.IotDataRepository;

@Service
public class DatabaseService implements PersistenceService {

    private final IotDataRepository iotDataRepository;

    public DatabaseService(IotDataRepository iotDataRepository) {
        this.iotDataRepository = iotDataRepository;
    }

    @Override
    public IotData save(IotData iotData) {
        return iotDataRepository.save(iotData);
    }

    @Override
    public IotData findById(Long id) {
        return iotDataRepository.findById(id).orElse(null);
    }

    @Override
    public List<IotData> findAll() {
        return iotDataRepository.findAll();
    }

    @Override
    public List<IotData> findByDataName(String dataName) {
        return iotDataRepository.findByDataName(dataName);
    }

    @Override
    public List<IotData> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return iotDataRepository.findByCreatedAtBetween(start, end);
    }

    @Override
    public List<IotData> findByDataRegistryContaining(String keyword) {
        return iotDataRepository.findByDataRegistryContaining(keyword);
    }

    @Override
    public List<IotData> searchByParams(String dataName, String dataRegistry, LocalDateTime createdAt) {
        return iotDataRepository.searchByParams(dataName, dataRegistry, createdAt);
    }

    @Override
    public void deleteById(Long id) {
        iotDataRepository.deleteById(id);
    }
}
