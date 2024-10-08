package br.com.joaogabriel.vendas.service.impl;

import br.com.joaogabriel.vendas.domain.entity.Cliente;
import br.com.joaogabriel.vendas.domain.entity.ItemPedido;
import br.com.joaogabriel.vendas.domain.entity.Pedido;
import br.com.joaogabriel.vendas.domain.entity.Produto;
import br.com.joaogabriel.vendas.domain.entity.enums.StatusPedido;
import br.com.joaogabriel.vendas.domain.repository.Clientes;
import br.com.joaogabriel.vendas.domain.repository.ItemsPedidos;
import br.com.joaogabriel.vendas.domain.repository.Pedidos;
import br.com.joaogabriel.vendas.domain.repository.Produtos;
import br.com.joaogabriel.vendas.exception.PedidoNaoEncontradoException;
import br.com.joaogabriel.vendas.exception.RegraNegocioException;
import br.com.joaogabriel.vendas.rest.dto.ItemPedidoDTO;
import br.com.joaogabriel.vendas.rest.dto.PedidoDTO;
import br.com.joaogabriel.vendas.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedidos itemsPedidosRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Codigo de cliente invalido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidosRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
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
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Nao e possivel realizar um pedido sem items.");
        }

        return items
                .stream()
                .map(dto -> {
                   Integer idProduto = dto.getProduto();
                   Produto produto = produtosRepository.findById(idProduto)
                           .orElseThrow(() -> new RegraNegocioException("Codigo de produto invalido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
