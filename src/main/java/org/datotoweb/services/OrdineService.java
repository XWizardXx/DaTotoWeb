package org.datotoweb.services;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.Utente;
import org.datotoweb.repositories.OrdineRepository;
import org.datotoweb.support.exeptions.OrderAlreadyExistsException;
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
    private OrdineRepository ordineRepo;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ordine creaOrdine(Ordine ordine) throws OrderAlreadyExistsException
    {
        if(ordineRepo.existsById(ordine.getId()))
            throw new OrderAlreadyExistsException();
        return ordineRepo.save(ordine);
    }

    @Transactional(readOnly = true)
    public List<Ordine> getOrdini()
    {
        return ordineRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ordine> getOrdineByUtente(Utente utente)
    {
        return ordineRepo.findOrdineByUtenteOrderBydata_ordineDesc(utente);
    }

    @Transactional(readOnly = true)
    public List<Ordine> getOrdineByData(Date data)
    {
        return ordineRepo.findBydata_ordineOrderBydata_ordineDesc(data);
    }
}
