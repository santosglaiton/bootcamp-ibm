package com.example.bootcampibm.repository;

import com.example.bootcampibm.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
