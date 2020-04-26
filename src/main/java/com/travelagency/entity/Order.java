package com.travelagency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date startBooking;

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date endBooking;
}
