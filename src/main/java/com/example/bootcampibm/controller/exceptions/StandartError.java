package com.example.bootcampibm.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandartError {

    private Integer status;

    private Long horario;

    private String mensagem;


}
