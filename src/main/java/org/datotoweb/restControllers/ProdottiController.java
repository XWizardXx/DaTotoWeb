package org.datotoweb.restControllers;

import javax.validation.Valid;
import org.datotoweb.models.Ordine;
import org.datotoweb.models.Prodotto;
import org.datotoweb.services.OrdineProdottoService;
import org.datotoweb.services.ProdottoService;
import org.datotoweb.support.exceptions.ProdottoEsistenteException;
import org.datotoweb.support.messages.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProdottiController
{
    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private OrdineProdottoService ordineProdottoService;

    @GetMapping("/paged")
    public ResponseEntity getProdottiDisponibili(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy)
    {
        List<Prodotto> ret = prodottoService.getProdottiDisponibili(pageNumber, pageSize, sortBy);
        if (ret.isEmpty())
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/search/by_name")
    public ResponseEntity getProdotto(@RequestParam(required = false) String name)
    {
        List<Prodotto> ret = prodottoService.getProdotto(name);
        if (ret.isEmpty())
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/{prodotto}/ordini")
    @PreAuthorize("hasRole('admin')")
    public List<Ordine> getOrdineByProdotto(@Valid @RequestBody Prodotto prodotto)
    {
        return ordineProdottoService.getOrdineByProdotto(prodotto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity create(@RequestBody @Valid Prodotto prodotto)
    {
        try
        {
            prodottoService.aggiungiProdotto(prodotto);
        } catch (ProdottoEsistenteException e)
        {
            return new ResponseEntity<>(new ResponseMessage("Prodotto Esistente!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Prodotto Aggiunto!"), HttpStatus.CREATED);
    }
}
