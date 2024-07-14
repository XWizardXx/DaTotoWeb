package org.datotoweb.repositories;

import org.datotoweb.models.Prodotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>
{
    @Query("SELECT P FROM Prodotto P WHERE P.nome LIKE ?1")
    List<Prodotto> findByNomeLike(String nome);

    @Modifying
    @Query("UPDATE Prodotto P SET P.quantita = ?1 WHERE P = ?2")
    void updateQuantita(int quantita, Prodotto p);

    @Query("SELECT P FROM Prodotto P WHERE P.quantita > 0")
    List<Prodotto> findByQuantitaPositiva(Pageable pageable);
}
