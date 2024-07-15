package org.datotoweb.restControllers;

import org.datotoweb.models.Prodotto;
import org.datotoweb.models.Utente;
import org.datotoweb.services.OrdineProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController
{
    @Autowired
    private OrdineProdottoService ordineProdottoService;

    @GetMapping
    public List<Prodotto> getPreferiti(@Valid Utente utente)
    {
        return ordineProdottoService.getPreferiti(utente);
    }

}
