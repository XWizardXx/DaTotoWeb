package org.datotoweb.services;

import org.datotoweb.models.Utente;
import org.datotoweb.repositories.UtenteRepository;
import org.datotoweb.support.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteService
{
    @Autowired
    private UtenteRepository utenteRepo;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente registraUtente(Utente utente) throws UserAlreadyExistException
    {
        if(utenteRepo.existsByEmail(utente.getEmail()))
            throw new UserAlreadyExistException();
        return utenteRepo.save(utente);
    }

    @Transactional(readOnly = true)
    public List<Utente> getUtenti()
    {
        return utenteRepo.findAll();
    }
}
