package com.example.bootcampibm.controller;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produto")
public class ProdutoController {


    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Void> insert(@Validated @RequestBody Produto produto){

        Produto obj = produtoService.insert(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
