package org.datotoweb.repositories;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>
{
    @Query("SELECT O FROM Ordine O, Utente U WHERE O.utente = U AND U.id_utente = ?1")
    List<Ordine> findOrdineByUtenteOrderBydata_ordineDesc(Utente utente);

    @Query("SELECT O FROM Ordine O WHERE O.data_ordine = ?1")
    List<Ordine> findBydata_ordineOrderBydata_ordineDesc(Date data_ordine);
}
