package sab.technologies.usermanagementservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Long clientId;

    private String contactNumber;

    private String address;

    private String city;

    private String state;

    private String zipCode;
    
    private String country;
}
