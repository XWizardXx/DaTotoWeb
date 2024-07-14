package org.datotoweb.repositories;

import org.datotoweb.models.Ordine;
import org.datotoweb.models.Utente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>
{
    @Query("SELECT O FROM Ordine O WHERE O.utente = ?1")
    List<Ordine> findOrdineByUtenteOrderBydata_ordineDesc(Utente utente, Pageable pageable);

    @Query("SELECT O FROM Ordine O WHERE O.data_ordine = ?1 AND O.utente = ?2")
    List<Ordine> findByDataUtente_ordineOrderBydata_ordineDesc(Date data_ordine, Utente utente, Pageable pageable);

}
