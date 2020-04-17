package com.travelagency.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_visas")
public class Visa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "visa_id")
  private Long id;

  @Column (name = "visa_name")
  private String name;

  @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL)
  private List<Country> countries;

}
