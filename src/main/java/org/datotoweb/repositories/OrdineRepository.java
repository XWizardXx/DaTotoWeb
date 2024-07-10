package org.datotoweb.repositories;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>
{
    List<Ordine> findOrdineByUtenteOrderBydata_ordineDesc(Utente utente);

    List<Ordine> findBydata_ordineOrderBydata_ordineDesc(Date data_ordine);
}
