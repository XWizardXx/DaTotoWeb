package org.datotoweb.services;

import org.datotoweb.models.Prodotto;
import org.datotoweb.repositories.ProdottoRepository;
import org.datotoweb.support.exceptions.ProdottoEsistenteException;
import org.datotoweb.support.exceptions.ProdottoNonEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoService
{
    @Autowired
    ProdottoRepository prodottoRepository;

    @Transactional(readOnly = false)
    public void aggiungiProdotto(Prodotto prodotto) throws ProdottoEsistenteException
    {
        if (prodotto != null && prodottoRepository.findById(prodotto.getId()).isEmpty())
            prodottoRepository.save(prodotto);
        else
            throw new ProdottoEsistenteException();
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getProdottiDisponibili(int numeroPagina, int dimensionePagina, String sortBy)
    {
        Pageable pageable = PageRequest.of(numeroPagina, dimensionePagina, Sort.by(sortBy));
        Page<Prodotto> prodotti = prodottoRepository.findAll(pageable);
        if (prodotti.hasContent())
            return prodotti.getContent();
        else
            return new ArrayList<Prodotto>();
    }

    @Transactional(readOnly = true)
    public void updateProdotto(int quantita, Prodotto p) throws ProdottoNonEsistenteException
    {
         if(prodottoRepository.findById(p.getId()).isPresent())
            prodottoRepository.updateQuantita(quantita, p);
         else
             throw new ProdottoNonEsistenteException();
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getProdotto(String nome)
    {
        return prodottoRepository.findByNomeLike(nome);
    }

}
