package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "clientstatustype")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientStatusType {

    @Id
    @GeneratedValue(generator="clientstatustype_seq")
    @SequenceGenerator(name="clientstatustype_seq",sequenceName="CLIENTSTATUSTYPE_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "name")
    private String name;


}