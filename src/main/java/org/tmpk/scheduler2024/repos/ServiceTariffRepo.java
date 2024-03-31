package org.tmpk.scheduler2024.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tmpk.scheduler2024.entity.ServiceTariff;
import org.tmpk.scheduler2024.entity.Tariffs;


import java.util.List;

@Repository
public interface ServiceTariffRepo extends JpaRepository<ServiceTariff,Long> {
    @Query(value = "SELECT * FROM ServiceTariff WHERE tariffsid = ?1", nativeQuery = true)
    List<ServiceTariff> findAllServiceTariffByTariffId(Long tariffId);
    @Query(value = "SELECT * FROM ServiceTariff WHERE clientid = ?1", nativeQuery = true)
    List<ServiceTariff> findAllServiceTariffByClientId(Long clientId);
}
