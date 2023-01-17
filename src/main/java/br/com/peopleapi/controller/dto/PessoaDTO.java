package br.com.peopleapi.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PessoaDTO(Long id, String nome, String dataNascimento, List<EnderecoDTO> enderecos) {
}
