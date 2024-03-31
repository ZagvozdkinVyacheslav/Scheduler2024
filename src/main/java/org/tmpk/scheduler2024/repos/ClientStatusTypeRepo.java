package org.tmpk.scheduler2024.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tmpk.scheduler2024.entity.ClientStatusType;

@Repository
public interface ClientStatusTypeRepo extends JpaRepository<ClientStatusType,Long> {
    @Query(value = "SELECT * FROM clientstatustype WHERE name = ?1", nativeQuery = true)
    ClientStatusType findByName(String name);
}
