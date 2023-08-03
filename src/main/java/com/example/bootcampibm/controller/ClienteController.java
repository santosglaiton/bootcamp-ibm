package com.example.bootcampibm.controller;


import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.dto.ClienteDto;
import com.example.bootcampibm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Void> insert(@Validated @RequestBody Cliente cliente){

        Cliente obj = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente cliente = service.find(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update (@RequestBody ClienteDto dto, @PathVariable Integer id){
        Cliente obj = service.fromDto(dto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }


}
