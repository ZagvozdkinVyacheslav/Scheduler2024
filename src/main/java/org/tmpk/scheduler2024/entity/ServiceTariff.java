package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servicetariff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceTariff {

    @Id
    @GeneratedValue(generator="servicetariff_seq")
    @SequenceGenerator(name="servicetariff_seq",sequenceName="SERVICETARIFF_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "clientid")
    private Long clientid;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "tariffsid",referencedColumnName = "id")
    private Tariffs tariffs;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "servicesid",referencedColumnName = "id")
    private Services services;
    @Column(name = "ispaided")
    private Boolean ispaided;

}