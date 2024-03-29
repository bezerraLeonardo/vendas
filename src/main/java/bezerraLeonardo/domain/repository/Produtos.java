package bezerraLeonardo.domain.repository;

import bezerraLeonardo.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
