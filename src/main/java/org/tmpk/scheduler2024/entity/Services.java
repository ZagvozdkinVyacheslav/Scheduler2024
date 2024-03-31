package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Services {
    @Id
    @GeneratedValue(generator="services_seq")
    @SequenceGenerator(name="services_seq",sequenceName="SERVICES_SEQ", allocationSize=1)
    private Long id;
    @Column(name = "name")
    private String name;

}
