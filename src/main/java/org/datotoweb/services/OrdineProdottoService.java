package org.datotoweb.services;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.OrdineProdotto;
import org.datotoweb.models.Prodotto;
import org.datotoweb.models.Utente;
import org.datotoweb.repositories.OrdineProdottoRepository;
import org.datotoweb.support.exceptions.OrdineProdottoEsistenteExcetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdineProdottoService
{
    @Autowired
    private OrdineProdottoRepository ordineProdottoRepository;

    @Transactional(readOnly = false)
    public void save(OrdineProdotto ordineProdotto) throws OrdineProdottoEsistenteExcetion
    {
        if(!ordineProdottoRepository.existsByKey(ordineProdotto.getKey()))
            ordineProdottoRepository.save(ordineProdotto);
        else
            throw new OrdineProdottoEsistenteExcetion();
    }

    @Transactional(readOnly = true)
    public List<OrdineProdotto> getByOrdine(Ordine ordine)
    {
        return ordineProdottoRepository.findByOrdine(ordine);
    }

    @Transactional(readOnly = true)
    public List<OrdineProdotto> getByProdotto(Prodotto prodotto)
    {
        return ordineProdottoRepository.findByProdotto(prodotto);
    }

    @Transactional(readOnly = true)
    public int getNumeroProdottoOrdine(Ordine ordine, Prodotto prodotto)
    {
        return ordineProdottoRepository.numeroProdottoInOrdine(ordine, prodotto);
    }
    @Transactional(readOnly = true)
    public List<Prodotto> getPreferiti(Utente utente)
    {
        return ordineProdottoRepository.findProdottoByUtenteMoreThan4OrderByNumeroNumeroDesc(utente);
    }
}
