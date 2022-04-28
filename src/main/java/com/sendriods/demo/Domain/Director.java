package com.sendriods.demo.Domain;

import javax.persistence.*;

@Entity
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "directorName")
    private String directorName;

    @Column(name = "directorId")
    private int directorId;
}
