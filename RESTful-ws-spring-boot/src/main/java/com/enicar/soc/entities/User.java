package com.enicar.soc.entities;

import com.enicar.soc.enums.Role;
import jakarta.persistence.*;


@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long lawyerId ;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String telephone;
    private Role role;
}
