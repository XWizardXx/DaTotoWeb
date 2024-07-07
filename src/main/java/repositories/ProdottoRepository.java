package repositories;

import model.Prodotto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>
{
    @Query("SELECT P FROM Prodotto P WHERE P.nome LIKE ?1")
    List<Prodotto> findByNomeLike(String nome, Pageable pageable);

    @Query("UPDATE Prodotto P SET P.quantita = ?1 WHERE P.nome LIKE ?2")
    int updateQuantita(int quantita, String nome);
}
