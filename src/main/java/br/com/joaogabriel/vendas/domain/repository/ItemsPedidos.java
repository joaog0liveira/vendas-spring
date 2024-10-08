package br.com.joaogabriel.vendas.domain.repository;

import br.com.joaogabriel.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {
}

