package com.travelagency.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table (name = "tb_countries")
public class Country {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column (name = "country_id")
  private Long id;

  @Column (name = "country_name")
  private String name;

  @ManyToOne
  @JoinColumn
  private Visa visa;

  @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
  private List<Hotel> hotels;

}
