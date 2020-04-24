package com.travelagency.dto;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RoomDto {

    private Long id;
    private String name;
    private boolean isFree;
    private Date startBooking;
    private Date endBooking;
}
