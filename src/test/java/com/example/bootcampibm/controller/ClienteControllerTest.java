package com.example.bootcampibm.controller;


import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.dto.ClienteDto;
import com.example.bootcampibm.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;


    private ClienteDto clienteDto;

    @BeforeEach
    public void setup(){
        clienteDto = new ClienteDto(1, "Joao", "joao@gmail.com");
    }


    @Test
    public void testInsertCliente() throws Exception{
        Cliente clienteInserido = new Cliente(1, "joao", "joao@gmail.com");

        when(clienteService.insert(any(Cliente.class))).thenReturn(clienteInserido);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nome\":\"joao\",\"email\":\"joao@gmail.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/clientes/1"));
    }

    @Test
    public void testDeleteCliente() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindClienteByIdExistente() throws Exception{
        Integer idCliente = 1;
        Cliente clienteEncontrado = new Cliente(idCliente, "Joao", "joao@gmail.com");

        when(clienteService.find(idCliente)).thenReturn(clienteEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Joao"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("joao@gmail.com"));
    }

}
