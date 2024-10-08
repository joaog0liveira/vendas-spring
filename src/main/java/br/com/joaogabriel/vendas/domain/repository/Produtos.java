package br.com.joaogabriel.vendas.domain.repository;

import br.com.joaogabriel.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produtos extends JpaRepository<Produto, Integer> {
}

