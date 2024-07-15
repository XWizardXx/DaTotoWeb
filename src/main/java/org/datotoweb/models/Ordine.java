package org.datotoweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ordine")
public class Ordine
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordine", nullable = false)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "utente", nullable = false, unique = true)
    private Utente utente;

    @Column(name = "prezzo", nullable = false)
    private float prezzo;

    @Column(name = "stato", nullable = false)
    private String stato;

    @Column(name = "data_ordine", nullable = false)
    private Date data_ordine;
}