package org.datotoweb.restControllers;

import org.datotoweb.models.Utente;
import org.datotoweb.services.UtenteService;
import org.datotoweb.support.exceptions.UserAlreadyExistException;
import org.datotoweb.support.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UtenteController
{
    private UtenteService utenteService;

    @GetMapping("/registration")
    public ResponseEntity registraUtente(Utente utente) throws UserAlreadyExistException
    {
        utenteService.registraUtente(utente);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity accediUtente(Utente utente) throws UserNotFoundException
    {
        utenteService.accediUtente(utente);
        return new ResponseEntity(HttpStatus.FOUND);
    }
}
