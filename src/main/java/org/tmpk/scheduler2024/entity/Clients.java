package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clients {
    @Id
    @GeneratedValue(generator="clients_seq")
    @SequenceGenerator(name="clients_seq",sequenceName="CLIENTS_SEQ", allocationSize=1)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balanceid",referencedColumnName = "id")
    private Balance balance;

    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "clientstatustypeid",referencedColumnName = "id")
    private ClientStatusType clientStatusType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientinfoid",referencedColumnName = "id")
    private ClientInfo clientInfo;


    @Column(name = "creatorid")
    private Long creatorId;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;
}
