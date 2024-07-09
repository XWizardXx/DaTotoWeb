package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordine_prodotto")
public class OrdineProdotto
{
    @Id
    @Column(name = "ordine", nullable = false)
    private long ordine;

    @Id
    @Column(name = "prodotto", nullable = false)
    private long prodotto;
}
