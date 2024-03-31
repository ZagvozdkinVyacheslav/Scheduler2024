package org.tmpk.scheduler2024.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tmpk.scheduler2024.entity.ServiceTariff;
import org.tmpk.scheduler2024.entity.Tariffs;

import java.util.List;


@Repository
public interface TariffsRepo extends JpaRepository<Tariffs,Long> {
    @Query(value = "SELECT * FROM tariffs WHERE howoftenpermonth = ?1", nativeQuery = true)
    List<Tariffs> findAllPerMonth(Integer count);
}
