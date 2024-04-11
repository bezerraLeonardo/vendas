package bezerraLeonardo.service.impl;


import bezerraLeonardo.domain.entity.Cliente;
import bezerraLeonardo.domain.entity.ItemPedido;
import bezerraLeonardo.domain.entity.Pedido;
import bezerraLeonardo.domain.entity.Produto;
import bezerraLeonardo.domain.enums.StatusPedido;
import bezerraLeonardo.domain.repository.Clientes;
import bezerraLeonardo.domain.repository.ItensPedido;
import bezerraLeonardo.domain.repository.Pedidos;
import bezerraLeonardo.domain.repository.Produtos;
import bezerraLeonardo.exception.PedidoNaoEncontradoException;
import bezerraLeonardo.exception.RegraNegocioException;
import bezerraLeonardo.rest.dto.ItemPedidoDTO;
import bezerraLeonardo.rest.dto.PedidoDTO;
import bezerraLeonardo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {  // implements PedidoService é usado para implementar a interface

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRespository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCLiente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCLiente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);


        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );

    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRespository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
