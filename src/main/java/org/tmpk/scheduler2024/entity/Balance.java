package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    @Id
    @GeneratedValue(generator="balance_seq")
    @SequenceGenerator(name="balance_seq",sequenceName="BALANCE_SEQ", allocationSize=1)

    private Long id;

    @Column(name = "balancevalue")
    private Double balanceValue;

    @Column(name = "limitvalue")
    private Double limitValue;

    @Column(name = "modified")
    private LocalDateTime modified;

}