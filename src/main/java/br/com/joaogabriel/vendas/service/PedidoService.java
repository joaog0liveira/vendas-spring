package br.com.joaogabriel.vendas.service;

import br.com.joaogabriel.vendas.domain.entity.Pedido;
import br.com.joaogabriel.vendas.domain.entity.enums.StatusPedido;
import br.com.joaogabriel.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
