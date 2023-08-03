package com.example.bootcampibm.repository;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {




}
