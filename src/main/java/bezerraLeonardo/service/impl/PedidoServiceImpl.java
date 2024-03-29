package bezerraLeonardo.service.impl;


import bezerraLeonardo.domain.repository.Pedidos;
import bezerraLeonardo.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {  // implements PedidoService é usado para implementar a interface

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
