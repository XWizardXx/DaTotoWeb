package org.datotoweb.repositories;

import org.datotoweb.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>
{
    @Query("SELECT U FROM Utente U WHERE U.e_mail like ?1")
    Utente findByEmailLike(String email);
}
