package bezerraLeonardo.domain.repository;

import bezerraLeonardo.domain.entity.Cliente;
import bezerraLeonardo.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
