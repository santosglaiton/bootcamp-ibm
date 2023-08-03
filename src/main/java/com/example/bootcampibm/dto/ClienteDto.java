package com.example.bootcampibm.dto;

import com.example.bootcampibm.domain.Cliente;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class ClienteDto implements Serializable {

    private Integer id;

    private String nome;

    private String email;

    public ClienteDto(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public ClienteDto(int id, String nome, String email) {
    }
}
