package org.datotoweb.restControllers;

import javax.validation.Valid;
import org.datotoweb.models.Ordine;
import org.datotoweb.models.OrdineProdotto;
import org.datotoweb.models.Prodotto;
import org.datotoweb.models.Utente;
import org.datotoweb.repositories.OrdineRepository;
import org.datotoweb.services.OrdineProdottoService;
import org.datotoweb.services.OrdineService;
import org.datotoweb.support.exceptions.OrderAlreadyExistsException;
import org.datotoweb.support.exceptions.OrdineProdottoEsistenteExcetion;
import org.datotoweb.support.messages.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrdiniController
{
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private OrdineProdottoService ordineProdottoService;
    @Autowired
    private OrdineRepository ordineRepository;

    @GetMapping("/paged")
    public ResponseEntity getOrdiniPaged(@Valid @RequestBody Utente utente, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy)
    {
        List<Ordine> ret = ordineService.getOrdineByUtente(utente, pageNumber, pageSize, sortBy);
        if (ret.isEmpty())
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/search/by_data")
    public ResponseEntity getOrdiniByDAta(Date data,@Valid @RequestBody Utente utente, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy)
    {
        List<Ordine> ret = ordineService.getOrdineByData(data, utente, pageNumber, pageSize, sortBy);
        if (ret.isEmpty())
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('admin')")
    public List<Ordine> getOrdini()
    {
        return ordineService.getOrdini();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity creaOrdine(@Valid @RequestBody Ordine ordine, List<OrdineProdotto> ordineProdottoList)
    {
        try
        {
            ordineService.creaOrdine(ordine);
            Set<OrdineProdotto> set = new TreeSet<>(ordineProdottoList);
            List<OrdineProdotto> lista = new ArrayList<>(set);
            for (@Valid OrdineProdotto ordineProdotto : lista)
                ordineProdottoService.save(ordineProdotto);
        } catch (OrderAlreadyExistsException e)
        {
            return new ResponseEntity<>(new ResponseMessage("Ordine non Aggiunto!"), HttpStatus.BAD_REQUEST);
        } catch (OrdineProdottoEsistenteExcetion e)
        {
            ordineRepository.delete(ordine);
            return new ResponseEntity<>(new ResponseMessage("Ordine non Aggiunto!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Ordine Aggiunto!"), HttpStatus.CREATED);
    }

    @GetMapping("/{ordine}/prodotti")
    @PreAuthorize("hasRole('admin')")
    public List<Prodotto> getProdottoByOrdine(@Valid @RequestBody Ordine ordine)
    {
        return ordineProdottoService.getProdottoByOrdine(ordine);
    }

    @GetMapping
    public int getNumeroProdotto(@Valid @RequestBody Ordine ordine ,@Valid @RequestBody Prodotto prodotto)
    {
        return ordineProdottoService.getNumeroProdottoOrdine(ordine, prodotto);
    }
}
