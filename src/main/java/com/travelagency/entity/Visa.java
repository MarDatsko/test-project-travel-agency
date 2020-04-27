package com.travelagency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_visas")
public class Visa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visa_name")
    private String name;

    @ManyToMany(mappedBy = "listVisas")
    private List<User> userList;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Country> countries;
}
