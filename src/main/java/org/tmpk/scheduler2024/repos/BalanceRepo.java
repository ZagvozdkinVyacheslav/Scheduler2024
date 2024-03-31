package org.tmpk.scheduler2024.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tmpk.scheduler2024.entity.Balance;


@Repository
public interface BalanceRepo extends JpaRepository<Balance,Long> {
}
