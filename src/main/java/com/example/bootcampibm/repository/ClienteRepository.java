package com.example.bootcampibm.repository;

import com.example.bootcampibm.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{


    // consulta personalizada

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);

}
