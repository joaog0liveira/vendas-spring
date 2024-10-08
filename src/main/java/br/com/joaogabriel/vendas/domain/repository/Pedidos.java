package br.com.joaogabriel.vendas.domain.repository;

import br.com.joaogabriel.vendas.domain.entity.Cliente;
import br.com.joaogabriel.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    //busque por id e traga os itens do pedido
    @Query("select p from Pedido p left join fetch p.itens where p.id =:id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
