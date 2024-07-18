package org.datotoweb.restControllers;

import javax.validation.Valid;
import org.datotoweb.models.Prodotto;
import org.datotoweb.models.Utente;
import org.datotoweb.services.OrdineProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController
{
    @Autowired
    private OrdineProdottoService ordineProdottoService;

    @GetMapping
    @PreAuthorize("hasRole('user')")
    public List<Prodotto> getPreferiti(@Valid @RequestBody Utente utente)
    {
        return ordineProdottoService.getPreferiti(utente);
    }

}
