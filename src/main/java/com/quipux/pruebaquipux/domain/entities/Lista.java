package com.quipux.pruebaquipux.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Lista {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombreLista")
  private String nombre;

  @Column(name = "descripcion")
  private String descripcion;

  @OneToMany(cascade = CascadeType.ALL)
  @Column(name = "Canciones")
  private List<Song> canciones;



}
