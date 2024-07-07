package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Setter
@Entity
@Table(name = "prodotto")
public class Prodotto
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prodotto", nullable = false)
    private int id_prodotto;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "quantita", nullable = false)
    private int quantita;

}
