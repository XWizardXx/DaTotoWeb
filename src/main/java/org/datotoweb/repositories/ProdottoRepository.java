package org.datotoweb.repositories;

import org.datotoweb.models.Prodotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>
{
    @Query("SELECT P FROM Prodotto P WHERE P.nome LIKE ?1")
    Prodotto findByNomeLike(String nome, Pageable pageable);

    @Modifying
    @Query("UPDATE Prodotto P SET P.quantita = ?1 WHERE P.nome LIKE ?2")
    int updateQuantita(int quantita, String nome);
}
