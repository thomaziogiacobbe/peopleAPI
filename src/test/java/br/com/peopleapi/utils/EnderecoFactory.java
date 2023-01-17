package br.com.peopleapi.utils;

import br.com.peopleapi.model.Endereco;

import java.util.List;

public class EnderecoFactory {

    private EnderecoFactory() {
    }

    public static Endereco createEndereco() {
        return Endereco
                .builder()
                .cidade("Australia")
                .cep("960101212")
                .logradouro("Rua dos Canguru")
                .numero(123)
                .build();
    }

    public static List<Endereco> createEnderecos() {
        return List.of(
                createEndereco(),
                Endereco.builder()
                .cidade("Pelotas")
                .cep("96010560")
                .logradouro("Rua Dr Joao Pessoa")
                .numero(312)
                .build());
    }
}
