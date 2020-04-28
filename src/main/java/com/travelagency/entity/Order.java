package com.travelagency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Room room;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startBooking;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endBooking;
}
