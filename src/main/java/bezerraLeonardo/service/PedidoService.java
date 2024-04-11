package bezerraLeonardo.service;

import bezerraLeonardo.domain.entity.Pedido;
import bezerraLeonardo.domain.enums.StatusPedido;
import bezerraLeonardo.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
