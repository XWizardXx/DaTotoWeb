package org.datotoweb.restControllers;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.Prodotto;
import org.datotoweb.models.Utente;
import org.datotoweb.services.OrdineService;
import org.datotoweb.support.messages.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdiniController
{
    @Autowired
    private OrdineService ordineService;

    @RequestMapping("/paged")
    public ResponseEntity getOrdiniPaged(Utente utente, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy)
    {
        List<Ordine> ret = ordineService.getOrdineByUtente(utente, pageNumber, pageSize, sortBy);
        if (ret.isEmpty())
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    // TODO

}
