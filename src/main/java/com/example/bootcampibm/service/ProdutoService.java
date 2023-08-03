package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.repository.ProdutoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto insert(Produto obj){
        obj.setId(null);
        obj = produtoRepository.save(obj);
        return obj;
    }

    public Produto find(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + Produto.class.getName()));
    }

}
