package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Pedido;
import com.example.bootcampibm.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setData(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        if (obj.getCliente() == null){
            throw new IllegalArgumentException("Cliente nao encontrado");
        }
        obj = pedidoRepository.save(obj);

        return obj;
    }

}
