package org.datotoweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utente")
public class Utente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente", nullable = false)
    private Long id_utente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "e_mail", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "passw", nullable = false)
    private String passw;

    @Column(name = "privilegio")
    private String privilegio;

    @OneToMany(mappedBy = "utente", orphanRemoval = true)
    private List<Ordine> ordini = new LinkedList<>();
}