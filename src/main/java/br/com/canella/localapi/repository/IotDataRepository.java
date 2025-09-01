package br.com.canella.localapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.canella.localapi.entity.IotData;

@Repository
public interface IotDataRepository extends JpaRepository<IotData, Long> {

        List<IotData> findByDataName(String dataName);

        List<IotData> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

        List<IotData> findByDataRegistryContaining(String keyword);

        @Query(value = "SELECT * FROM iotdata WHERE " +
                        "(:dataName IS NULL OR data_name = :dataName) AND " +
                        "(:dataRegistry IS NULL OR data_registry LIKE CONCAT('%', :dataRegistry, '%')) AND " +
                        "(:createdAt IS NULL OR created_at = :createdAt)", nativeQuery = true)
        List<IotData> searchByParams(@Param("dataName") String dataName,
                        @Param("dataRegistry") String dataRegistry,
                        @Param("createdAt") LocalDateTime createdAt);
}
