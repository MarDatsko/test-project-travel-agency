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
    @Column(name = "visa_id")
    private Long id;

    @Column(name = "visa_name")
    private String name;

    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL)
    private List<Country> countries;

}
