package repositories;

import model.OrdineProdotto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineProdottoRepository
{
    List<OrdineProdotto> findByNome(String nome);
}
