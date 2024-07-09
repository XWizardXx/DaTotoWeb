package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prodotto")
public class Prodotto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prodotto", nullable = false)
    private Long id;

    @Column(name = "prezzo_unitario", nullable = false)
    private Double prezzo_unitario;

    @Column(name = "descrizione", nullable = false, unique = true, length = 512)
    private String descrizione;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;


    @Column(name = "quantita", nullable = false)
    private String quantita;

}