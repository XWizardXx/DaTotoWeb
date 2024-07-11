package org.datotoweb.services;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.Utente;
import org.datotoweb.repositories.OrdineRepository;
import org.datotoweb.support.exceptions.OrderAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrdineService
{
    @Autowired
    private OrdineRepository ordineRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ordine creaOrdine(Ordine ordine) throws OrderAlreadyExistsException
    {
        if(ordineRepository.existsById(ordine.getId()))
            throw new OrderAlreadyExistsException();
        return ordineRepository.save(ordine);
    } 

    @Transactional(readOnly = true)
    public List<Ordine> getOrdini()
    {
        return ordineRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ordine> getOrdineByUtente(Utente utente)
    {
        return ordineRepository.findOrdineByUtenteOrderBydata_ordineDesc(utente);
    }

    @Transactional(readOnly = true)
    public List<Ordine> getOrdineByData(Date data)
    {
        return ordineRepository.findBydata_ordineOrderBydata_ordineDesc(data);
    }
}