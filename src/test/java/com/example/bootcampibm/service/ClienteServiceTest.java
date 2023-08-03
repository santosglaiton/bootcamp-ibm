package com.example.bootcampibm.service;


import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.repository.ClienteRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;


    @Test
    public void testInsertCliente(){
        Cliente cliente = new Cliente(1, "nome", "teste");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente clienteInserido = clienteService.insert(cliente);

        assertNotNull(clienteInserido);
        assertEquals(cliente.getNome(), clienteInserido.getNome());
        assertEquals(cliente.getEmail(), clienteInserido.getEmail());
        assertNull(clienteInserido.getId());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testFindClienteByIdExistente(){
        Integer clienteId = 1;
        Cliente cliente = new Cliente(clienteId, "Maria", "maria@gmail.com");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.find(clienteId);

        assertNotNull(clienteEncontrado);
        assertEquals(cliente.getId(), clienteEncontrado.getId());
        assertEquals(cliente.getNome(), clienteEncontrado.getNome());
        assertEquals(cliente.getEmail(), clienteEncontrado.getEmail());

        verify(clienteRepository, times(1)).findById(clienteId);

    }

    @Test
    public void testFindClienteByIdNaoExistente(){
        Integer clienteId = 99;

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> clienteService.find(clienteId));
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    public void testDeleteCliente(){
        Integer clienteId = 1;
        clienteService.delete(clienteId);
        verify(clienteRepository, times(1)).deleteById(clienteId);

    }

    @Test
    public void testUpdateCliente(){
        Integer clienteId = 1;
        Cliente clienteAtualizado = new Cliente(clienteId, "Maria", "maria@gmail.com");
        Cliente clienteExistente = new Cliente(clienteId, "Joao", "joao@gmail.com");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.update(clienteAtualizado);

        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getId());
        assertEquals(clienteAtualizado.getNome(), resultado.getNome());
        assertEquals(clienteAtualizado.getEmail(), resultado.getEmail());

        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }


}
