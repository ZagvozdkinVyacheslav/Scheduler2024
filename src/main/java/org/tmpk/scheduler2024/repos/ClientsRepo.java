package org.tmpk.scheduler2024.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tmpk.scheduler2024.entity.Clients;


@Repository
public interface ClientsRepo extends JpaRepository<Clients,Long> {
}
