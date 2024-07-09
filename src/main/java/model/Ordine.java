package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente", nullable = false, unique = true)
    private Cliente cliente;

    @Column(name = "prezzo", nullable = false)
    private double prezzo;

    @Column(name = "stato", nullable = false)
    private String stato;

    @Column(name = "data_ordine", nullable = false)
    private String data_ordine;


}