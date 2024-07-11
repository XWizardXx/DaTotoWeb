package org.datotoweb.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.datotoweb.support.embeddables.OrdineProdottoKey;

@Getter
@Setter
@Entity
@Table(name = "ordine_prodotto")
public class OrdineProdotto
{
    @EmbeddedId
    OrdineProdottoKey key;

    @ManyToOne(optional = false)
    @MapsId("prodotto")
    @JoinColumn(name = "prodotto", nullable = false)
    private Prodotto prodotto;


    @ManyToOne(optional = false)
    @MapsId("ordine")
    @JoinColumn(name = "ordine", nullable = false)
    private Ordine ordine;

    @Column(name = "numero")
    private int numero;
}
