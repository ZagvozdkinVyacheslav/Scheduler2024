package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tariffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tariffs {

    @Id
    @GeneratedValue(generator="tariffs_seq")
    @SequenceGenerator(name="tariffs_seq",sequenceName="TARIFFS_SEQ", allocationSize=1)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "howoftenpermonth")
    private Integer howoftenpermonth;


}