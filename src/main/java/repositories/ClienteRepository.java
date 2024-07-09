package repositories;

import model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Utente, Long>
{
    @Query("SELECT U FROM Utente U WHERE U.e_mail like ?1")
    Utente findByEmailLike(String email);
}
