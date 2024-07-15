package org.datotoweb.repositories;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.OrdineProdotto;
import org.datotoweb.models.Prodotto;
import org.datotoweb.models.Utente;
import org.datotoweb.support.embeddables.OrdineProdottoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineProdottoRepository extends JpaRepository<OrdineProdotto, Integer>
{
    @Query("SELECT OP.ordine FROM OrdineProdotto OP WHERE OP.prodotto = ?1")
    public List<Ordine> findOrdineByProdotto(Prodotto prodotto);

    @Query("SELECT OP.prodotto FROM OrdineProdotto OP WHERE OP.ordine = ?1")
    public List<Prodotto> findProdottoByOrdine(Ordine ordine);

    @Query("SELECT OP.numero FROM OrdineProdotto OP WHERE OP.ordine = ?1 AND OP.prodotto = ?2")
    public int numeroProdottoInOrdine(Ordine ordine, Prodotto prodotto);

    @Query("SELECT OP.prodotto FROM OrdineProdotto OP WHERE OP.ordine.utente = ?1 GROUP BY OP.ordine.utente HAVING COUNT (*)>4")
    public List<Prodotto> findProdottoByUtenteMoreThan4OrderByNumeroNumeroDesc(Utente utente);
    boolean existsByKey(OrdineProdottoKey key);
}
