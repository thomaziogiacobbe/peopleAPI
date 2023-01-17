package br.com.peopleapi.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PessoaDTO(Long id, String nome, String dataNascimento, List<EnderecoDTO> enderecos) {

}
