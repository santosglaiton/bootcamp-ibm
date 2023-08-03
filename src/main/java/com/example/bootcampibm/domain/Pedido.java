package com.example.bootcampibm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //@OneToMany(mappedBy = "id.pedido")
    //private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Integer id, Date data, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
    }
}
