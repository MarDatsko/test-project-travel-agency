package com.travelagency.entity;

import com.travelagency.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
