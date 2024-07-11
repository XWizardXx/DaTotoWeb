package org.datotoweb.support.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class OrdineProdottoKey implements Serializable
{
    @Column(name = "ordine")
    private long ordine;

    @Column(name = "prodotto")
    private long prodotto;

    @Override
    public int hashCode()
    {
        int M = 31;
        return (int) (ordine * M + prodotto * M);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof OrdineProdottoKey)
            return this.ordine == ((OrdineProdottoKey) obj).ordine && this.prodotto == ((OrdineProdottoKey) obj).prodotto;
        return false;
    }
}
