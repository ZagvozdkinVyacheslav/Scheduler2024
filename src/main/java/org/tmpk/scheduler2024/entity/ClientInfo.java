package org.tmpk.scheduler2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "clientinfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfo {

    @Id
    @GeneratedValue(generator="clientinfo_seq")
    @SequenceGenerator(name="clientinfo_seq",sequenceName="CLIENTINFO_SEQ", allocationSize=1)

    private Long id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "organisationname")
    private String organisationName;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "city")
    private String city;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "buildingnumber")
    private String buildingNumber;

    @Column(name = "flatnumber")
    private String flatNumber;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "clienttypeid",referencedColumnName = "id")
    private ClientType clientType;

}
