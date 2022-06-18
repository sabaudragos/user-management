package sab.technologies.usermanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
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
